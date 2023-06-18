package com.danielbenami.dragontail.model;

import com.danielbenami.dragontail.converter.DateTimeConverter;
import com.danielbenami.dragontail.dto.StockData;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;
    private LocalDateTime date;

    public static Stock withProperDateTime(StockData stockData) {
        Stock stock = new Stock();
        stock.setName(stockData.getName());
        stock.setPrice(stockData.getPrice());
        stock.setDate(DateTimeConverter.convertUnixTimestamp(stockData.getDate()));
        return stock;
    }
}
