package com.example.seriesupdater_.series.updater.mappers;

import com.example.seriesclient.seriesclient.contract.configuration.LanguageDetailsDto;
import com.example.seriesdata.model.Language;

import com.example.seriesupdater_.series.updater.mappers.interfaces.IMap;
import org.springframework.stereotype.Component;

@Component
public class LanguageMapper implements IMap<LanguageDetailsDto, Language> {
    @Override
    public Language toEntity(LanguageDetailsDto dto) {
        Language language = new Language();
        language.setEnglishName(dto.getEnglishName());
        language.setNativeName(dto.getNativeName());

        return language;
    }
}