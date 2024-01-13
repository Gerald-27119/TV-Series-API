package com.example.seriesclient.seriesclient.contract;

import com.example.seriesclient.seriesclient.contract.configuration.LanguageDetailsDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
public class SeriesDetailsDto extends SeriesSummaryDto{
    @JsonProperty("name")
    private String name;
    @JsonProperty("overview")
    private String overview;
    @JsonProperty("adult")
    private boolean adult;
    @JsonProperty("in_production")
    private boolean inProduction;
    @JsonProperty("number_of_seasons")
    private int numberOfSeasons;
    @JsonProperty("number_of_episodes")
    private int numberOfEpisodes;
    @JsonProperty("popularity")
    private int popularity;
    @JsonProperty("vote_count")
    private int voteCount;
    @JsonProperty("vote_average")
    private double voteAverage;
    @JsonProperty("status")
    private String status;
    @JsonProperty("genres")
    private List<GenreDto> genres;
    @JsonProperty("first_air_date")
    private LocalDate firstAirDate;
    @JsonProperty("networks")
    private List<NetworkDto> networks;
    @JsonProperty("production_countries")
    private List<ProductionCountrySummaryDto> productionCountries;
    @JsonProperty("seasons")
    private List<SeasonSummaryDto> seasons;
    @JsonProperty("spoken_languages")
    private List<LanguageDetailsDto> languages;

}
