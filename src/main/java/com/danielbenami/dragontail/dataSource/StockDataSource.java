package com.danielbenami.dragontail.dataSource;

import com.danielbenami.dragontail.dto.StockData;
import com.danielbenami.dragontail.model.Stock;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public interface StockDataSource {
    List<Stock> fetchData();

    static StockDataSource liveData(String endpoint, RestTemplate restTemplate, Logger logger) {
        return () -> {
            try {
                ResponseEntity<List<StockData>> response = restTemplate.exchange(
                        endpoint,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {
                        }
                );
                List<StockData> stockDataList = response.getBody();
                return stockDataList.stream()
                        .map(Stock::withProperDateTime)
                        .collect(Collectors.toList());
            } catch (Exception e) {
                logger.error("Failed to fetch data from live data source: {}", e.getMessage());
                return Collections.emptyList();
            }
        };
    }

    static StockDataSource jsonData(String filePath, ObjectMapper objectMapper, Logger logger) {
        return () -> {
            try {
                TypeReference<List<StockData>> typeReference = new TypeReference<>() {
                };
                InputStream inputStream = TypeReference.class.getResourceAsStream(filePath);
                List<StockData> stockDataList = objectMapper.readValue(inputStream, typeReference);
                return stockDataList.stream()
                        .map(Stock::withProperDateTime)
                        .collect(Collectors.toList());
            } catch (IOException e) {
                logger.error("Failed to fetch data from JSON data source: {}", e.getMessage());
                return Collections.emptyList();
            }
        };
    }
}






