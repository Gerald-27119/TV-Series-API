package com.example.seriesclient.seriesclient.contract.configuration;

import com.example.seriesclient.seriesclient.contract.GenreDto;

import java.util.List;

public class GenresListDto {

    public List<GenreDto> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreDto> genres) {
        this.genres = genres;
    }

    private List<GenreDto> genres;
}
