package com.danielbenami.dragontail;

import com.danielbenami.dragontail.dataSource.JSONDataSource;
import com.danielbenami.dragontail.model.Stock;
import com.danielbenami.dragontail.repository.StockRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationRunner implements CommandLineRunner {

    private final JSONDataSource jsonDataSource;
    private final StockRepository stockRepository;

    public ApplicationRunner(JSONDataSource jsonDataSource, StockRepository stockRepository) {
        this.jsonDataSource = jsonDataSource;
        this.stockRepository = stockRepository;
    }

    @Override
    public void run(String... args) {
        List<Stock> stocks = jsonDataSource.fetchData();
        stockRepository.saveAll(stocks);
    }
}
