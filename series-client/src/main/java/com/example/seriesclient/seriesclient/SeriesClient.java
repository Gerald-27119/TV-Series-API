package com.example.seriesclient.seriesclient;

import com.example.seriesclient.seriesclient.contract.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SeriesClient implements ISeriesClient {

    RestTemplate restClient;
    String baseUrl;
    String apiKey;
    int version;
    private final ISeriesClientSettings settings;

    public SeriesClient(ISeriesClientSettings seriesClientSettings) {
        restClient = new RestTemplate();
        this.baseUrl = seriesClientSettings.getBaseUrl();
        this.apiKey = seriesClientSettings.getApiKey();
        this.version = seriesClientSettings.getApiVersion();
        settings = seriesClientSettings;
    }

    @Override
    public SeriesDetailsDto getSeriesDetails(long id) {
        String uri = settings.getUrlBuilder()
                .pathSegment("tv", id + "")
                .build()
                .toUriString();
        return restClient.getForEntity(uri, SeriesDetailsDto.class).getBody();
    }


    @Override
    public SeasonDetailsDto getSeasonDetails(long seriesId, int seasonNumber) {
        String uri = settings.getUrlBuilder()
                .pathSegment("tv", seriesId + "")
                .pathSegment("season")
                .pathSegment(seasonNumber + "")
                .build()
                .toUriString();
        return restClient.getForEntity(uri, SeasonDetailsDto.class).getBody();
    }


    @Override
    public EpisodeDetailsDto getEpisodeDetails(long seriesId, int seasonNumber, int episodeNumber) {
        String uri = settings.getUrlBuilder()
                .pathSegment("tv", seriesId + "")
                .pathSegment("season")
                .pathSegment(seasonNumber + "")
                .pathSegment("episode")
                .pathSegment(episodeNumber + "")
                .build()
                .toUriString();
        return restClient.getForEntity(uri, EpisodeDetailsDto.class).getBody();
    }

    @Override
    public PagedResultDto getPopularFirstPage() {
        return getPopular(1);
    }

    @Override
    public PagedResultDto getPopular(int page) {
        String uri = settings.getUrlBuilder()
                .pathSegment("tv")
                .pathSegment("popular")
                .queryParam("page", page)
                .build()
                .toUriString();
        return restClient.getForEntity(uri, PagedResultDto.class).getBody();
    }
}
