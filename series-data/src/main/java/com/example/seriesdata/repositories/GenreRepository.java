package com.example.seriesdata.repositories;

import com.example.seriesdata.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    public Genre findByName(String name);
}
