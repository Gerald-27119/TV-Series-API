package com.example.seriesclient.seriesclient.contract.configuration;

import com.example.seriesclient.seriesclient.contract.GenreDto;
import lombok.Getter;

import java.util.List;

@Getter
public class GenresListDto {
    private List<GenreDto> genres;

}
