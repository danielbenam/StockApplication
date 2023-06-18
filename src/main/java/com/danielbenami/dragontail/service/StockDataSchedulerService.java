package com.danielbenami.dragontail.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class StockDataSchedulerService {

    private final StockService stockService;

    public StockDataSchedulerService(StockService stockService) {
        this.stockService = stockService;
    }

    @Scheduled(fixedRateString = "${scheduler.fixedRate}", initialDelayString = "${scheduler.initialDelay}") // Run every 10 seconds
    public void fetchLatestStockData() {
        stockService.fetchLatestStockData();
    }
}
