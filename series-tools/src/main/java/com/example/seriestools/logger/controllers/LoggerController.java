package com.example.seriestools.logger.controllers;


import com.example.seriestools.logger.service.LoggerService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("logs/updater")
public class LoggerController {

    private final LoggerService loggerService;


    @GetMapping(value = "download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity downloadFile() throws IOException {
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment;filename="
                        + loggerService.getFileName())
                .contentLength(loggerService.getFileLength())
                .body(loggerService.getLogFile());
    }
}