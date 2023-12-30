package com.example.seriesclient;

import com.example.seriesclient.seriesclient.ISeriesClient;
import com.example.seriesclient.seriesclient.ISeriesConfigurationClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SeriesClientApplication implements CommandLineRunner {
    private final ISeriesClient seriesClient;
    private final ISeriesConfigurationClient seriesConfigurationClient;
    public SeriesClientApplication(ISeriesClient seriesClient, ISeriesConfigurationClient seriesConfigurationClient) {
        this.seriesClient = seriesClient;
        this.seriesConfigurationClient = seriesConfigurationClient;
    }
    public static void main(String[] args) {
        SpringApplication.run(SeriesClientApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        //Main.test(seriesClient);
    }

}
