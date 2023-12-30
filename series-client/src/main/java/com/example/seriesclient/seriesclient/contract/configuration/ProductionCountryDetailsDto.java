package com.example.seriesclient.seriesclient.contract.configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
public class ProductionCountryDetailsDto {
    @JsonProperty("english_name")
    private String englishName;
    @JsonProperty("native_name")
    private String nativeName;

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
