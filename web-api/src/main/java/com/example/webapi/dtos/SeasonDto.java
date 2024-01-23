package com.example.webapi.dtos;

import com.example.seriesclient.seriesclient.contract.EpisodeSummaryDto;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;
public record SeasonDto(
        String name,

        String overview,

        String posterPath,

        int seasonNumber,

        double voteAverage,

        LocalDate airDate,

        List<EpisodeDto>episodes

) {
}
