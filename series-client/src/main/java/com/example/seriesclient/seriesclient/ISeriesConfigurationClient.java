package com.example.seriesclient.seriesclient;

import com.example.seriesclient.seriesclient.contract.GenreDto;
import com.example.seriesclient.seriesclient.contract.configuration.LanguageDetailsDto;
import com.example.seriesclient.seriesclient.contract.configuration.ProductionCountryDetailsDto;

import java.util.List;

public interface ISeriesConfigurationClient {
    List<LanguageDetailsDto> getLanguages();
    List<GenreDto> getGenres();
    List<ProductionCountryDetailsDto> getCountries();
}
