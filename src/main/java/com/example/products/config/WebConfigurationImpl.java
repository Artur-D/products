package com.example.products.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("web")
public class WebConfigurationImpl implements WebConfiguration {

    private int queryResultLimit;

    public int getQueryResultLimit() {
        return queryResultLimit;
    }

    public void setQueryResultLimit(int queryResultLimit) {
        this.queryResultLimit = queryResultLimit;
    }

    @Override
    public int provideQueryResultLimit() {
        return queryResultLimit;
    }
}
