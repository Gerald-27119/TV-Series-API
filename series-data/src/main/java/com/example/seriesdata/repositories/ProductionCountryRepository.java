package com.example.seriesdata.repositories;

import com.example.seriesdata.model.ProductionCountry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionCountryRepository extends JpaRepository<ProductionCountry, Long> {
    ProductionCountry findTopByName(String name);
    ProductionCountry findByName(String name);
    Boolean existsByName(String name);
}
