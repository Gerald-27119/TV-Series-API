package com.example.seriestools.logger.controllers;

import com.example.seriestools.logger.service.LoggerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/logs")
public class LoggerController {

    private final LoggerService loggerService;

    public LoggerController(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    @GetMapping
    public List<LogDto> getLogs() throws IOException {
        return loggerService.getLogs()
                .map(this::parseLog)
                .collect(Collectors.toList());
    }

    private LogDto parseLog(String logLine) {
        String[] parts = logLine.split(" ", 3);
        LocalDateTime timestamp = LocalDateTime.parse(parts[0], DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        String level = parts[1];
        String message = parts[2];
        return new LogDto(timestamp, level, message);
    }
}