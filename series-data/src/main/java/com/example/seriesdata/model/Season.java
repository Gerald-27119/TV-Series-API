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
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String sourceId;

    private String name;

    private String overview;

    private String posterPath;

    private int seasonNumber;

    private double voteAverage;

    private LocalDate airDate;

    @OneToMany(mappedBy = "season", cascade = CascadeType.ALL)
    private List<Episode> episodes = new ArrayList<>();

    @ManyToOne
    private Series series;

}
