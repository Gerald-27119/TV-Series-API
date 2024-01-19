package com.example.seriesclient.seriesclient.contract.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class LanguageDetailsDto {
    @JsonProperty("name")
    private String nativeName;
    @JsonProperty("english_name")
    private String englishName;

}
