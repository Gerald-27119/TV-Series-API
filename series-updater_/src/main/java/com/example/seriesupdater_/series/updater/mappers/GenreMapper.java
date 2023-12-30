package com.example.seriesupdater_.series.updater.mappers;

import com.example.seriesclient.seriesclient.contract.GenreDto;
import com.example.seriesdata.model.Genre;

import com.example.seriesupdater_.series.updater.mappers.interfaces.IMap;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper implements IMap<GenreDto, Genre> {
    @Override
    public Genre toEntity(GenreDto dto) {
        Genre genre = new Genre();
        genre.setSourceId(dto.getId());
        genre.setName(dto.getName());

        return genre;
    }
}
