package com.example.seriesclient.seriesclient;

import com.example.seriesclient.seriesclient.contract.GenreDto;
import com.example.seriesclient.seriesclient.contract.configuration.GenresListDto;
import com.example.seriesclient.seriesclient.contract.configuration.LanguageDetailsDto;
import com.example.seriesclient.seriesclient.contract.configuration.ProductionCountryDetailsDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class SeriesConfigurationClient implements ISeriesConfigurationClient{

    RestTemplate restClient;
    String baseUrl;
    String apiKey;
    int version;
    private final ISeriesClientSettings settings;

    public SeriesConfigurationClient(ISeriesClientSettings settings) {
        restClient = new RestTemplate();
        this.baseUrl=settings.getBaseUrl();
        this.apiKey= settings.getApiKey();
        this.version= settings.getApiVersion();
        this.settings=settings;
    }

    @Override
    public List<LanguageDetailsDto> getLanguages() {
        String url = settings.getUrlBuilder()
                .pathSegment("configuration", "languages")
                .build()
                .toUriString();
        return restClient.exchange(url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<ArrayList<LanguageDetailsDto>>() {})
                .getBody();
    }

    @Override
    public List<GenreDto> getGenres() {
        String url = settings.getUrlBuilder()
                .pathSegment("genre", "tv", "list")
                .build()
                .toUriString();
        return restClient.getForObject(url, GenresListDto.class).getGenres();
    }

    @Override
    public List<ProductionCountryDetailsDto> getCountries() {
        String url = settings.getUrlBuilder()
                .pathSegment("configuration", "countries")
                .build()
                .toUriString();
        return restClient.exchange(url,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<ArrayList<ProductionCountryDetailsDto>>() {})
                .getBody();
    }
}
