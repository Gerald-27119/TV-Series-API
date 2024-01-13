package com.example.seriesclient.seriesclient.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EpisodeDetailsDto extends EpisodeSummaryDto {

    @JsonProperty("air_date")
    private LocalDate airDate;
    @JsonProperty("season_number")
    private int seasonNumber;
    @JsonProperty("runtime")
    private int runtime;

}


