package com.example.seriesdata.repositories;

import com.example.seriesdata.model.Series;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeriesRepository extends JpaRepository<Series, Long> {
    public Optional<Series> findBySourceId(long sourceId);
}
