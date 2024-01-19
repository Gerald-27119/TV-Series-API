package com.example.seriesdata.repositories;

import com.example.seriesdata.model.Network;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NetworkRepository extends JpaRepository<Network, Long> {
    Network findByName(String name);
}
