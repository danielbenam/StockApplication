package com.danielbenami.dragontail.dataSource;

import com.danielbenami.dragontail.config.DataSourceProperties;
import com.danielbenami.dragontail.dto.StockData;
import com.danielbenami.dragontail.model.Stock;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JSONDataSource implements StockDataSource {

    private static final Logger logger = LoggerFactory.getLogger(JSONDataSource.class);
    private final DataSourceProperties properties;
    private final ObjectMapper objectMapper;

    public JSONDataSource(DataSourceProperties properties, ObjectMapper objectMapper) {
        this.properties = properties;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Stock> fetchData() {
        StockDataSource jsonDataSource = StockDataSource.jsonData(properties.getJsonFilepath(), objectMapper, logger);
        return jsonDataSource.fetchData();
    }
}
