package com.danielbenami.dragontail.service;

import com.danielbenami.dragontail.dataSource.StockDataSource;
import com.danielbenami.dragontail.model.Stock;
import com.danielbenami.dragontail.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final List<StockDataSource> stockDataSources;

    @Autowired
    public StockServiceImpl(StockRepository stockRepository, List<StockDataSource> stockDataSources) {
        this.stockRepository = stockRepository;
        this.stockDataSources = stockDataSources;
    }

    @Override
    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    @Override
    public List<Stock> getStocksByNames(List<String> names) {
        return stockRepository.findByNameIn(names);
    }

    public void fetchLatestStockData() {
        List<CompletableFuture<List<Stock>>> futures = stockDataSources.stream()
                .map(dataSource -> CompletableFuture.supplyAsync(dataSource::fetchData))
                .collect(Collectors.toList());

        List<Stock> allStocks = futures.stream()
                .flatMap(future -> future.join().stream())
                .collect(Collectors.toList());

        Map<String, Stock> latestStockMap = allStocks.stream()
                .collect(Collectors.toMap(
                        Stock::getName,
                        Function.identity(),
                        (s1, s2) -> s1.getDate().isAfter(s2.getDate()) ? s1 : s2
                ));

        List<Stock> latestStockData = new ArrayList<>(latestStockMap.values());
        synchronizeStockData(latestStockData);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void synchronizeStockData(List<Stock> stockData) {
        for (Stock stock : stockData) {
            Optional<Stock> existingStockOptional = stockRepository.findByName(stock.getName());
            if (existingStockOptional.isEmpty()) {
                stockRepository.save(stock);
            } else if (stock.getDate().isAfter(existingStockOptional.get().getDate())) {
                final Stock existingStock = existingStockOptional.get();
                existingStock.setPrice(stock.getPrice());
                existingStock.setDate(stock.getDate());
                stockRepository.save(existingStock);
            }
        }
    }
}
