package com.example.webapi.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.experimental.Accessors;

import java.time.LocalDate;

public record EpisodeDto(
        String name,
        LocalDate airDate,
        int seasonNumber,
        int runtime
) {
}
