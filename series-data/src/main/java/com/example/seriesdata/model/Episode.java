package com.example.seriesdata.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.time.LocalDate;

@Entity
@Data
@RequiredArgsConstructor
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long sourceId;

    private String name;

    private LocalDate airDate;

    private int seasonNumber;

    private int episodeNumber;

    private int runtime;

    @ManyToOne
    private Season season;

}
