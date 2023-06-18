package com.danielbenami.dragontail.dataSource;

import com.danielbenami.dragontail.converter.DateTimeConverter;
import com.danielbenami.dragontail.model.Stock;
import com.danielbenami.dragontail.model.StockUpdatedData;
import com.danielbenami.dragontail.repository.StockUpdatedDataRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SQLDataSource implements StockDataSource {

    private final StockUpdatedDataRepository stockUpdatedDataRepository;

    public SQLDataSource(StockUpdatedDataRepository stockUpdatedDataRepository) {
        this.stockUpdatedDataRepository = stockUpdatedDataRepository;
    }

    @Override
    public List<Stock> fetchData() {
        List<Stock> stocks = new ArrayList<>();
        List<StockUpdatedData> stockUpdatedDataList = stockUpdatedDataRepository.findAll();

        for (StockUpdatedData stockUpdatedData : stockUpdatedDataList) {
            Stock stock = new Stock();
            stock.setName(stockUpdatedData.getName());
            stock.setPrice(stockUpdatedData.getPrice());
            stock.setDate(DateTimeConverter.convertUnixTimestamp(stockUpdatedData.getDate()));
            stocks.add(stock);
        }
        return stocks;
    }
}
