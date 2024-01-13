package com.example.seriesdata.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@RequiredArgsConstructor
public class Episode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long sourceId;
    private String name;
    private String overview;
    private LocalDate airDate;
    private int seasonNumber;
    private int episodeNumber;
    private int runtime;
    private double voteAverage;
    private int voteCount;
    private String stillPath;
    private String productionCode;
    @ManyToOne
    private Season season;

}
