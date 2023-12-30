package com.example.seriesdata;


import com.example.seriesdata.repositories.*;
import org.springframework.stereotype.Repository;

@Repository
public class SeriesDataCatalog implements ICatalogData {
    String nothing = "nothing";
    private final GenreRepository genres;
    private final LanguageRepository languages;
    private final NetworkRepository networks;
    private final ProductionCountryRepository productionCountries;
    private final SeasonRepository seasons;
    private final SeriesRepository series;
    private final EpisodeRepository episodes;


    public SeriesDataCatalog(GenreRepository genres,
                             LanguageRepository languages, NetworkRepository networks,
                             ProductionCountryRepository productionCountries,
                             SeasonRepository seasons, SeriesRepository series, EpisodeRepository episodes) {
        this.episodes = episodes;
        this.genres = genres;
        this.languages = languages;
        this.networks = networks;
        this.productionCountries = productionCountries;
        this.seasons = seasons;
        this.series = series;
    }


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

