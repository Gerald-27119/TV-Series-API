package com.example.webapi.dtos;

import com.example.seriesclient.seriesclient.contract.ProductionCountrySummaryDto;
import com.example.seriesclient.seriesclient.contract.SeasonSummaryDto;
import com.example.seriesclient.seriesclient.contract.configuration.LanguageDetailsDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;

public record SeriesDto(
        long id,
        String name,
        boolean adult,
        boolean inProduction,
        int numberOfSeasons,
        int numberOfEpisodes,
        double voteAverage,
        double popularity,
        int voteCount,
        LocalDate firstAirDate,
        String status,
        List<GenreDto> genres,

        List<NetworkDto> networks,

        List<ProductionCountryDto> productionCountries,

        List<SeasonDto> seasons,

        List<LanguageDto> languages

) {
}