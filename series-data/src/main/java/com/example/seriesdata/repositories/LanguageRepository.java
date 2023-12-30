package com.example.seriesdata.repositories;

import com.example.seriesdata.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    public Language findByEnglishName(String englishName);
}
