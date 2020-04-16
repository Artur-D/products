package com.example.products.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

public interface WebConfiguration {

    String BASE_API_PATH = "${web.rest-api-url}";

    int provideQueryResultLimit();

}
