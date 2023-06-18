package com.danielbenami.dragontail.service;

import com.danielbenami.dragontail.model.Stock;

import java.util.List;

public interface StockService {

    List<Stock> getAllStocks();

    List<Stock> getStocksByNames(List<String> names);

    void fetchLatestStockData();
}
