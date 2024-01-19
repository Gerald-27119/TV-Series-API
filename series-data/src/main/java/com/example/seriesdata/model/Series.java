package com.example.seriesdata.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long sourceId;

    private String originalName;

    private String name;

    private boolean adult;

    private boolean inProduction;

    private int numberOfSeasons;

    private int numberOfEpisodes;

    private String overview;

    private double voteAverage;

    private double popularity;

    private int voteCount;

    private LocalDate firstAirDate;

    private String status;

    @ManyToMany(mappedBy = "series", cascade = CascadeType.ALL)
    private List<Genre> genres = new ArrayList<>();

    @ManyToMany(mappedBy = "series", cascade = CascadeType.ALL)
    private List<Language> languages = new ArrayList<>();

    @ManyToMany(mappedBy = "series", cascade = CascadeType.ALL)
    private List<ProductionCountry> productionCountries = new ArrayList<>();

    @ManyToMany(mappedBy = "series", cascade = CascadeType.ALL)
    private List<Network> networks = new ArrayList<>();

    @OneToMany(mappedBy = "series", cascade = CascadeType.ALL)
    private List<Season> seasons = new ArrayList<>();
}
