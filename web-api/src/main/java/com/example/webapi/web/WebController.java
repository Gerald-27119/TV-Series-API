package com.example.webapi.web;

import com.example.webapi.dtos.SeriesDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/series")
@RequiredArgsConstructor
public class WebController {
    private final WebService webService;

    @GetMapping
    public ResponseEntity<List<SeriesDto>> getAllSeries() {
        return ResponseEntity.ok(webService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeriesDto> getSeriesById(@PathVariable Long id) {
        return ResponseEntity.ok(webService.getSeriesById(id));
    }

    @PostMapping
    public ResponseEntity<SeriesDto> createSeries(@RequestBody SeriesDto seriesDto) {
        return ResponseEntity.ok(webService.createSeries(seriesDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeries(@PathVariable Long id) {
        webService.deleteSeries(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeriesDto> updateSeries(@PathVariable Long id, @RequestBody SeriesDto seriesDto) {
        return ResponseEntity.ok(webService.putSeries(id, seriesDto));
    }
}