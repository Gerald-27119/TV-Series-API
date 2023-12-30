package com.example.seriesdata.repositories;

import com.example.seriesdata.model.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {

}
