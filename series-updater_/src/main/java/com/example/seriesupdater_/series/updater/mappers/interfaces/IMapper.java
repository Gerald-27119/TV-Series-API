package com.example.seriesupdater_.series.updater.mappers.interfaces;

import com.example.seriesclient.seriesclient.contract.*;
import com.example.seriesclient.seriesclient.contract.configuration.LanguageDetailsDto;
import com.example.seriesclient.seriesclient.contract.configuration.ProductionCountryDetailsDto;
import com.example.seriesdata.model.*;

public interface IMapper {
    IMap<NetworkDto, Network> network();
    IMap<GenreDto, Genre> genre();
    IMap<ProductionCountryDetailsDto, ProductionCountry> productionCountry();
    IMap<LanguageDetailsDto, Language> language();
    IMap<SeasonDetailsDto, Season> season();
    IMap<SeriesDetailsDto,Series> series();
    IMap<EpisodeDetailsDto, Episode> episode();
}
