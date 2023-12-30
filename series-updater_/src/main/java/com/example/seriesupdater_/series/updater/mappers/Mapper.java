package com.example.seriesupdater_.series.updater.mappers;

import com.example.seriesclient.seriesclient.contract.*;
import com.example.seriesclient.seriesclient.contract.configuration.LanguageDetailsDto;
import com.example.seriesclient.seriesclient.contract.configuration.ProductionCountryDetailsDto;
import com.example.seriesdata.model.*;
import com.example.seriesupdater_.series.updater.mappers.interfaces.IMap;
import com.example.seriesupdater_.series.updater.mappers.interfaces.IMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapper implements IMapper {
    IMap<NetworkDto, Network> network;
    IMap<GenreDto, Genre> genre;
    IMap<ProductionCountryDetailsDto, ProductionCountry> productionCountry;
    IMap<LanguageDetailsDto, Language> language;
    IMap<SeasonDetailsDto, Season> season;
    IMap<SeriesDetailsDto,Series> series;
    IMap<EpisodeDetailsDto, Episode> episode;


    public Mapper(IMap<NetworkDto, Network> network, IMap<GenreDto, Genre> genre,
                  IMap<ProductionCountryDetailsDto, ProductionCountry> productionCountry,
                  IMap<LanguageDetailsDto, Language> language,
                  IMap<SeasonDetailsDto, Season> season, IMap<SeriesDetailsDto, Series> series,
                  IMap<EpisodeDetailsDto, Episode> episode) {
        this.network = network;
        this.genre = genre;
        this.productionCountry = productionCountry;
        this.language = language;
        this.season = season;
        this.series = series;
        this.episode = episode;
    }

    @Override
    public IMap<NetworkDto, Network> network() {
        return network;
    }

    @Override
    public IMap<GenreDto, Genre> genre() {
        return genre;
    }

    @Override
    public IMap<ProductionCountryDetailsDto, ProductionCountry> productionCountry() {
        return productionCountry;
    }

    @Override
    public IMap<LanguageDetailsDto, Language> language() {
        return language;
    }

    @Override
    public IMap<SeasonDetailsDto, Season> season() {
        return season;
    }

    @Override
    public IMap<SeriesDetailsDto, Series> series() {
        return series;
    }

    @Override
    public IMap<EpisodeDetailsDto, Episode> episode() {
        return episode;
    }
}
