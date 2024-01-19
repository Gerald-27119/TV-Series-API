package com.example.seriesupdater_.series.updater;

import com.example.seriesclient.seriesclient.ISeriesClient;
import com.example.seriesclient.seriesclient.contract.EpisodeDetailsDto;
import com.example.seriesclient.seriesclient.contract.SeasonDetailsDto;
import com.example.seriesclient.seriesclient.contract.SeriesDetailsDto;
import com.example.seriesdata.ICatalogData;
import com.example.seriesdata.model.*;
import com.example.seriesupdater_.series.updater.mappers.interfaces.IMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class SeriesComponent {
    private final IMapper mapper;
    private final ISeriesClient client;
    private final ICatalogData dbCatalog;
    private static final Logger log = LoggerFactory.getLogger(SeriesUpdater.class);


    @Transactional
    public void saveLanguages(SeriesDetailsDto seriesDetailsDto, Series series) {
        seriesDetailsDto.getLanguages().forEach(languageDto -> {
            Language language = dbCatalog.getLanguages().findByEnglishName(languageDto.getEnglishName());
            language.getSeries().add(series);
            series.getLanguages().add(language);
        });
    }

    @Transactional
    public void saveGenres(SeriesDetailsDto seriesDetailsDto, Series series) {
        seriesDetailsDto.getGenres().forEach(genreDto -> {
            Genre genre = dbCatalog.getGenres().findByName(genreDto.getName());
            genre.getSeries().add(series);
            series.getGenres().add(genre);
        });
    }

    @Transactional
    public void saveProductionCountries(SeriesDetailsDto seriesDetailsDto, Series series) {
        seriesDetailsDto.getProductionCountries().forEach(countryDto -> {
            ProductionCountry country = dbCatalog.getProductionCountries().findByName(countryDto.getName());
            country.getSeries().add(series);
            series.getProductionCountries().add(country);
        });
    }

    @Transactional
    public void saveNetwork(SeriesDetailsDto seriesDetailsDto, Series series) {
        seriesDetailsDto.getNetworks().forEach(networkDto -> {
            Network network = mapper.network().toEntity(networkDto);
            network.getSeries().add(series);
            dbCatalog.getNetworks().save(network);
            series.getNetworks().add(network);
        });
    }

    @Transactional
    public void saveEpisodesAndSeasons(SeriesDetailsDto seriesDetailsDto, Series series) {
        seriesDetailsDto.getSeasons().forEach(seasonSummaryDto -> {
            var seriesId = seriesDetailsDto.getId();
            var seasonNumber = seasonSummaryDto.getSeasonNumber();

            SeasonDetailsDto seasonDetailsDto = client.getSeasonDetails(seriesId, seasonNumber);
            Season season = mapper.season().toEntity(seasonDetailsDto);

            seasonDetailsDto.getEpisodes().forEach(episodeSummaryDto -> {
                var episodeNumber = episodeSummaryDto.getEpisodeNumber();

                EpisodeDetailsDto episodeDetailsDto = client.getEpisodeDetails(seriesId, seasonNumber, episodeNumber);
                Episode episode = mapper.episode().toEntity(episodeDetailsDto);
                episode.setSeason(season);
                dbCatalog.getEpisodes().save(episode);
                season.getEpisodes().add(episode);
            });
            season.setSeries(series);
            dbCatalog.getSeasons().save(season);
            series.getSeasons().add(season);

        });
    }
}
