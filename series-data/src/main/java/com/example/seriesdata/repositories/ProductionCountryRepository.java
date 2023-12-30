package com.example.seriesdata.repositories;

import com.example.seriesdata.model.ProductionCountry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionCountryRepository extends JpaRepository<ProductionCountry, Long> {
    public ProductionCountry findByName(String name);
}
