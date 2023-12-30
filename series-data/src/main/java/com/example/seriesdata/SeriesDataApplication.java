package com.example.seriesdata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication(scanBasePackages = {"com.example.seriesdata"})
public class SeriesDataApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeriesDataApplication.class, args);
    }

}
