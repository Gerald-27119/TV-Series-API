package com.example.seriesupdater_.series.updater;


import com.example.seriesclient.seriesclient.ISeriesClient;
import com.example.seriesclient.seriesclient.ISeriesConfigurationClient;
import com.example.seriesclient.seriesclient.contract.EpisodeDetailsDto;
import com.example.seriesclient.seriesclient.contract.SeasonDetailsDto;
import com.example.seriesclient.seriesclient.contract.SeriesDetailsDto;
import com.example.seriesclient.seriesclient.contract.SeriesSummaryDto;
import com.example.seriesdata.ICatalogData;
import com.example.seriesdata.model.*;
import com.example.seriesupdater_.series.updater.mappers.interfaces.IMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class SeriesUpdater implements ISeriesUpdater {
    private final IMapper mapper;
    private final ISeriesClient client;
    private final ISeriesConfigurationClient configClient;
    private final ICatalogData dbCatalog;
    private final TransactionTemplate transactionTemplate;
    private static final Logger log = LoggerFactory.getLogger(SeriesUpdater.class);

    public void updateConfig() {
        log.info("Starting updateConfig...");

        CompletableFuture<Void> languagesFuture = CompletableFuture.runAsync(() -> {
            log.info("Updating languages...");
            List<Language> languagesToSave = new ArrayList<>();
            configClient.getLanguages().forEach(languageDto -> {
                Language language = mapper.language().toEntity(languageDto);
                languagesToSave.add(language);
            });
            dbCatalog.getLanguages().saveAll(languagesToSave);
        });

        CompletableFuture<Void> genresFuture = CompletableFuture.runAsync(() -> {
            log.info("Updating genres...");
            List<Genre> genresToSave = new ArrayList<>();
            configClient.getGenres().forEach(genreDto -> {
                Genre genre = mapper.genre().toEntity(genreDto);
                genresToSave.add(genre);
            });
            dbCatalog.getGenres().saveAll(genresToSave);
        });

        CompletableFuture<Void> countriesFuture = CompletableFuture.runAsync(() -> {
            log.info("Updating production countries...");
            List<ProductionCountry> countriesToSave = new ArrayList<>();
            configClient.getCountries().forEach(countryDto -> {
                ProductionCountry productionCountry = mapper.productionCountry().toEntity(countryDto);
                countriesToSave.add(productionCountry);
            });
            dbCatalog.getProductionCountries().saveAll(countriesToSave);
        });

        CompletableFuture.allOf(languagesFuture, genresFuture, countriesFuture).join();

        log.info("Finished updateConfig.");
    }

    @Override
    @Transactional
    public void updateByPopularity(int firstPage, int lastPage) {
        log.info("Starting updateByPopularity for pages from " + firstPage + " to " + lastPage + "...");
        updateConfig();

        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for(int i = firstPage; i <= lastPage; i++) {
            client.getPopular(i).getResults().forEach(seriesSummaryDto -> {
                CompletableFuture<Void> seriesFuture = CompletableFuture.runAsync(() -> {
                    transactionTemplate.execute(status -> {
                        var sourceId = seriesSummaryDto.getId();
                        Series series = dbCatalog.getSeries().findBySourceId(sourceId).orElse(null);
                        if (series == null) {
                            saveSeries(seriesSummaryDto);
                        }
                        else {
                            log.info("Series with source ID = {} already exists in database", sourceId);
                        }
                        return null;
                    });
                });
                futures.add(seriesFuture);
            });
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

        log.info("Finished updateByPopularity.");
    }
    @Transactional
    public void saveSeries(SeriesSummaryDto seriesSummaryDto) {
        SeriesDetailsDto seriesDetailsDto = client.getSeriesDetails(seriesSummaryDto.getId());
        Series series = mapper.series().toEntity(seriesDetailsDto);

        saveLanguages(seriesDetailsDto, series);
        saveGenres(seriesDetailsDto, series);
        saveProductionCountries(seriesDetailsDto, series);
        saveNetwork(seriesDetailsDto, series);
        saveEpisodesAndSeasons(seriesDetailsDto, series);

        dbCatalog.getSeries().save(series);
        log.info("Series with source ID = {} saved to database", series.getSourceId());
    }

    public void saveLanguages(SeriesDetailsDto seriesDetailsDto, Series series) {
        seriesDetailsDto.getLanguages().forEach(languageDto -> {
            Language language = dbCatalog.getLanguages().findByEnglishName(languageDto.getEnglishName());
            language.getSeries().add(series);
            series.getLanguages().add(language);
        });
    }


    public void saveGenres(SeriesDetailsDto seriesDetailsDto, Series series) {
        seriesDetailsDto.getGenres().forEach(genreDto -> {
            Genre genre = dbCatalog.getGenres().findByName(genreDto.getName());
            genre.getSeries().add(series);
            series.getGenres().add(genre);
        });
    }

    public void saveProductionCountries(SeriesDetailsDto seriesDetailsDto, Series series) {
        seriesDetailsDto.getProductionCountries().forEach(countryDto -> {
            ProductionCountry country = dbCatalog.getProductionCountries().findByName(countryDto.getName());
            country.getSeries().add(series);
            series.getProductionCountries().add(country);
        });
    }

    public void saveNetwork(SeriesDetailsDto seriesDetailsDto, Series series) {
        seriesDetailsDto.getNetworks().forEach(networkDto -> {
            Network network = dbCatalog.getNetworks().findByName(networkDto.getName());
            if (network == null) {
                network = mapper.network().toEntity(networkDto);
                network.getSeries().add(series);
               dbCatalog.getNetworks().save(network);
            }
            series.getNetworks().add(network);
        });
    }

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
