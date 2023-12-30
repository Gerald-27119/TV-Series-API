package com.example.seriesupdater_.series.updater.mappers;

import com.example.seriesclient.seriesclient.contract.configuration.ProductionCountryDetailsDto;
import com.example.seriesdata.model.ProductionCountry;

import com.example.seriesupdater_.series.updater.mappers.interfaces.IMap;
import org.springframework.stereotype.Component;

@Component
public class ProductionCountryMapper implements IMap<ProductionCountryDetailsDto, ProductionCountry> {
    @Override
    public ProductionCountry toEntity(ProductionCountryDetailsDto dto) {//to sjest smieszne bo CountryDto przenosi przeciez tez native name
        ProductionCountry productionCountry = new ProductionCountry();
        productionCountry.setName(dto.getEnglishName());

        return productionCountry;
    }
}
