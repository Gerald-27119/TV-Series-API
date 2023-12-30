package com.example.seriesdata.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String englishName;
    private String nativeName;

    @ManyToMany
    private List<Series> series = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public List<Series> getSeries() {
        return series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }



    public List<Series> getMovies() {
        return series;
    }

    public void setMovies(List<Series> series) {
        this.series = series;
    }
}