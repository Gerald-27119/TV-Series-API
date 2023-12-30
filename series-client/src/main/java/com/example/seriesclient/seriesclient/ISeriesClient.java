package com.example.seriesclient.seriesclient;

import com.example.seriesclient.seriesclient.contract.EpisodeDetailsDto;
import com.example.seriesclient.seriesclient.contract.PagedResultDto;
import com.example.seriesclient.seriesclient.contract.SeasonDetailsDto;
import com.example.seriesclient.seriesclient.contract.SeriesDetailsDto;

public interface ISeriesClient {
    SeriesDetailsDto getSeriesDetails(long id);

    SeasonDetailsDto getSeasonDetails(long seriesId, int seasonNumber);

    EpisodeDetailsDto getEpisodeDetails(long seriesId, int seasonNumber, int episodeNumber);

    PagedResultDto getPopularFirstPage();

    PagedResultDto getPopular(int page);
}
