package com.example.seriesclient.seriesclient.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeriesSummaryDto {
    @JsonProperty("id")
    private long id;
    @JsonProperty("original_name")
    private String originalName;

}
