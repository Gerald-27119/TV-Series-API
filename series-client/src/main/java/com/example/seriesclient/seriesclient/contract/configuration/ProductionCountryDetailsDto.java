package com.example.seriesclient.seriesclient.contract.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ProductionCountryDetailsDto {
    @JsonProperty("english_name")
    private String englishName;
    @JsonProperty("native_name")
    private String nativeName;

}
