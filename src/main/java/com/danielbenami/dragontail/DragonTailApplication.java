package com.danielbenami.dragontail;

import com.danielbenami.dragontail.config.DataSourceProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@EnableConfigurationProperties(DataSourceProperties.class)
@SpringBootApplication
public class DragonTailApplication {


    public static void main(String[] args) {
        SpringApplication.run(DragonTailApplication.class, args);
    }

}
