package com.example.seriesclient.seriesclient.contract;

import com.example.seriesclient.seriesclient.contract.configuration.LanguageDetailsDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

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

    public List<LanguageDetailsDto> getLanguages() {
        return languages;
    }

    public void setLanguages(List<LanguageDetailsDto> languages) {
        this.languages = languages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public boolean isInProduction() {
        return inProduction;
    }

    public void setInProduction(boolean inProduction) {
        this.inProduction = inProduction;
    }

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public int getNumberOfEpisodes() {
        return numberOfEpisodes;
    }

    public void setNumberOfEpisodes(int numberOfEpisodes) {
        this.numberOfEpisodes = numberOfEpisodes;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public LocalDate getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(LocalDate firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public List<GenreDto> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreDto> genres) {
        this.genres = genres;
    }

    public List<NetworkDto> getNetworks() {
        return networks;
    }

    public void setNetworks(List<NetworkDto> networks) {
        this.networks = networks;
    }

    public List<ProductionCountrySummaryDto> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<ProductionCountrySummaryDto> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public List<SeasonSummaryDto> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<SeasonSummaryDto> seasons) {
        this.seasons = seasons;
    }
}
