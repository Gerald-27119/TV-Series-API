package com.example.seriesupdater_.series.controllers;

import com.example.seriestools.logger.service.LoggerService;
import com.example.seriesupdater_.series.updater.ISeriesUpdater;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequestMapping("updater")
public class UpdaterController {
    private final ISeriesUpdater seriesUpdater;
    private final LoggerService loggerService;

    public UpdaterController(ISeriesUpdater seriesUpdater, LoggerService loggerService) {
        this.seriesUpdater = seriesUpdater;
        this.loggerService = loggerService;
    }

    @GetMapping("update/popular")
    public ResponseEntity update(@RequestParam int firstPage, @RequestParam int lastPage) {
        new Thread(() -> seriesUpdater.updateByPopularity(firstPage,lastPage)).start();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "logs", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity downloadLogFile() throws IOException {
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment;filename="
                        + loggerService.getFileName())
                .contentLength(loggerService.getFileLength())
                .body(loggerService.getLogFile());
    }
}