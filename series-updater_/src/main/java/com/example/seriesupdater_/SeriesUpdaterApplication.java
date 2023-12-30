package com.example.seriesupdater_;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication(scanBasePackages = {"com.example.seriesclient", "com.example.seriesupdater_","com.example.seriesdata"})
public class SeriesUpdaterApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeriesUpdaterApplication.class, args);
    }

}
