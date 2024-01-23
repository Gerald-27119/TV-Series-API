package com.example.webapi.dtos;

import lombok.Getter;
import lombok.experimental.Accessors;
public record LanguageDto(
        String name,
        String englishName
) {
}
