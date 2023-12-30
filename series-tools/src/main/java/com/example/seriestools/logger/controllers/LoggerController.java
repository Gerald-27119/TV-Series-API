package com.example.seriestools.logger.controllers;

import com.example.seriestools.logger.service.LoggerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;

@RestController
@RequestMapping("/logs")
public class LoggerController {

    private final LoggerService loggerService;

    public LoggerController(LoggerService loggerService) {
        this.loggerService = loggerService;
    }

    @GetMapping
    public String getLogs() throws IOException {
        return loggerService.getLogs().reduce("", (s1, s2) -> s1 + "\n" + s2);
    }
}