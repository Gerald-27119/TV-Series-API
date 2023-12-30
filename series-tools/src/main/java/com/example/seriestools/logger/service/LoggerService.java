package com.example.seriestools.logger.service;

import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class LoggerService {

    private static final String LOG_FILE_PATH = "logs.log";

    public Stream<String> getLogs() throws IOException {
        return Files.lines(Paths.get(LOG_FILE_PATH));
    }
}