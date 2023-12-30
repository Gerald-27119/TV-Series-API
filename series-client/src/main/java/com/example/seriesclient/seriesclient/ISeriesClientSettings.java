package com.example.seriesclient.seriesclient;

import org.springframework.web.util.UriComponentsBuilder;

public interface ISeriesClientSettings {
    String getApiKey();
    String getBaseUrl();
    int getApiVersion();
    default UriComponentsBuilder getUrlBuilder(){
        return UriComponentsBuilder
                .newInstance()
                .scheme("http")
                .host(getBaseUrl())
                .pathSegment(getApiVersion() + "")
                .queryParam("api_key", getApiKey());
    }
}
