package com.example.webapi.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;
public record NetworkDto(
        String name,
        String logoPath,
        String originCountry
) {
}
