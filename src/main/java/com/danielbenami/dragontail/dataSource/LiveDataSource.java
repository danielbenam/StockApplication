package com.danielbenami.dragontail.dataSource;

import com.danielbenami.dragontail.config.DataSourceProperties;
import com.danielbenami.dragontail.dto.StockData;
import com.danielbenami.dragontail.model.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LiveDataSource implements StockDataSource {

    private static final Logger logger = LoggerFactory.getLogger(LiveDataSource.class);
    private final RestTemplate restTemplate;
    private final DataSourceProperties properties;

    public LiveDataSource(RestTemplate restTemplate, DataSourceProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    @Override
    public List<Stock> fetchData() {
        StockDataSource liveDataSource = StockDataSource.liveData(properties.getLiveDataEndpoint(), restTemplate, logger);
        return liveDataSource.fetchData();
    }

}
