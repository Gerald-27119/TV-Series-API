package com.example.seriesclient.seriesclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SeriesClientSettings implements ISeriesClientSettings {
    private final String apiKey;
    private final String baseUrl;
    private final int apiVersion;

    public SeriesClientSettings(
            @Value("67cf6c481090cb3f6b929e68a8d601b0") String apiKey,
            @Value("api.themoviedb.org") String baseUrl,
            @Value("3") int apiVersion) {

        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
        this.apiVersion = apiVersion;
    }

    @Override
    public String getApiKey() {
        return apiKey;
    }

    @Override
    public String getBaseUrl() {
        return baseUrl;
    }

    @Override
    public int getApiVersion() {
        return apiVersion;
    }
}
