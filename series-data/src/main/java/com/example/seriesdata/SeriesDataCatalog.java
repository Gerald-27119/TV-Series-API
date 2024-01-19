package com.example.seriesdata;


import com.example.seriesdata.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SeriesDataCatalog implements ICatalogData {
    private final GenreRepository genres;
    private final LanguageRepository languages;
    private final NetworkRepository networks;
    private final ProductionCountryRepository productionCountries;
    private final SeasonRepository seasons;
    private final SeriesRepository series;
    private final EpisodeRepository episodes;
    @Override
    public EpisodeRepository getEpisodes() {
        return episodes;
    }
    @Override
    public GenreRepository getGenres() {
        return genres;
    }
    @Override
    public LanguageRepository getLanguages() {
        return languages;
    }
    @Override
    public NetworkRepository getNetworks() {
        return networks;
    }
    @Override
    public ProductionCountryRepository getProductionCountries() {
        return productionCountries;
    }
    @Override
    public SeasonRepository getSeasons() {
        return seasons;
    }
    @Override
    public SeriesRepository getSeries() {
        return series;
    }
}

