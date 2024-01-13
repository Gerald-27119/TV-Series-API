package com.example.seriesclient.seriesclient.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
public class SeasonDetailsDto  {

    @JsonProperty("_id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("overview")
    private String overview;
    @JsonProperty("poster_path")
    private String posterPath;
    @JsonProperty("season_number")
    private int seasonNumber;
    @JsonProperty("vote_average")
    private double voteAverage;
    @JsonProperty("air_date")
    private LocalDate airDate;
    @JsonProperty("episodes")
    private List<EpisodeSummaryDto> episodes;

}
