package com.danielbenami.dragontail.controller;

import com.danielbenami.dragontail.dto.StockDTO;
import com.danielbenami.dragontail.mapper.StockMapper;
import com.danielbenami.dragontail.model.Stock;
import com.danielbenami.dragontail.service.StockService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final StockService stockService;
    private final StockMapper stockMapper;

    public StockController(StockService stockService, StockMapper stockMapper) {
        this.stockService = stockService;
        this.stockMapper = stockMapper;
    }

    @GetMapping
    public List<StockDTO> getAllStocks() {
        List<Stock> stocks = stockService.getAllStocks();
        return stockMapper.toDTOList(stocks);
    }

    @GetMapping(params = "names")
    public List<StockDTO> getStocksByNames(@RequestParam List<String> names) {
        List<Stock> stocks = stockService.getStocksByNames(names);
        return stockMapper.toDTOList(stocks);
    }
}
