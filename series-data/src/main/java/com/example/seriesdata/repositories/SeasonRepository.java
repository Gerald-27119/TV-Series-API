package com.example.seriesdata.repositories;

import com.example.seriesdata.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeasonRepository extends JpaRepository<Season, Long> {
}
