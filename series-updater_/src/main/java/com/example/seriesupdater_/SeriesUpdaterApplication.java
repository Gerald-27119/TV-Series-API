package com.example.seriesupdater_;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@EnableJpaRepositories
@SpringBootApplication(scanBasePackages = {"com.example.seriesclient", "com.example.seriesupdater_","com.example.seriesdata"})
public class SeriesUpdaterApplication {

    public static void main(String[] args) {
        System.setProperty("appLifeCycle", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
        SpringApplication.run(SeriesUpdaterApplication.class, args);
    }

}
