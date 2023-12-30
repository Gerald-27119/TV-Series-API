package com.example.seriesclient.seriesclient.contract.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LanguageDetailsDto {
    @JsonProperty("name")
    private String nativeName;
    @JsonProperty("english_name")
    private String englishName;

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }
}
