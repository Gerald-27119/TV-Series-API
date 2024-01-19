package com.example.seriesupdater_.series.updater;

import com.example.seriesclient.seriesclient.ISeriesConfigurationClient;
import com.example.seriesdata.ICatalogData;
import com.example.seriesdata.model.Genre;
import com.example.seriesdata.model.Language;
import com.example.seriesdata.model.ProductionCountry;
import com.example.seriesupdater_.series.updater.mappers.interfaces.IMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class ConfigurationComponent {
    private static final Logger log = LoggerFactory.getLogger(SeriesUpdater.class);
    private final IMapper mapper;
    private final ISeriesConfigurationClient configClient;
    private final ICatalogData dbCatalog;


    public void updateConfiguration() {
        CompletableFuture<Void> languagesFuture = CompletableFuture.runAsync(this::updateLanguages);
        CompletableFuture<Void> genresFuture = CompletableFuture.runAsync(this::updateGenres);
        CompletableFuture<Void> countriesFuture = CompletableFuture.runAsync(this::updateProductionCountries);

        CompletableFuture.allOf(languagesFuture, genresFuture, countriesFuture).join();
    }
    @Transactional
    protected void updateLanguages() {
        log.info("Updating languages...");

        List<Language> languagesToSave = new ArrayList<>();
        configClient.getLanguages().stream()
                .map(mapper.language()::toEntity)
                .forEach(languagesToSave::add);

        dbCatalog.getLanguages().saveAll(languagesToSave);
    }
    @Transactional
    protected void updateGenres() {
        log.info("Updating genres...");

        List<Genre> genresToSave = new ArrayList<>();
        configClient.getGenres().stream()
                .map(mapper.genre()::toEntity)
                .forEach(genresToSave::add);

        dbCatalog.getGenres().saveAll(genresToSave);
    }
    @Transactional
    protected void updateProductionCountries() {
        log.info("Updating production countries...");

        List<ProductionCountry> countriesToSave = new ArrayList<>();
        configClient.getCountries().stream()
                .map(mapper.productionCountry()::toEntity)
                .forEach(countriesToSave::add);

        dbCatalog.getProductionCountries().saveAll(countriesToSave);
    }
}