package com.example.seriesdata;

import com.example.seriesdata.repositories.*;

public interface ICatalogData {
    EpisodeRepository getEpisodes();
    GenreRepository getGenres();
    LanguageRepository getLanguages();
    NetworkRepository getNetworks();
    ProductionCountryRepository getProductionCountries();
    SeasonRepository getSeasons();
    SeriesRepository getSeries();

}
