package com.danielbenami.dragontail.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "datasource")
public class DataSourceProperties {

    private String jsonFilepath;
    private String liveDataEndpoint;

}

