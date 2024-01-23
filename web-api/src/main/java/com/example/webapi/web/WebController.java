package com.example.webapi.web;

import com.example.webapi.dtos.*;
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
    @GetMapping("/genres")
    public ResponseEntity<List<GenreDto>> getAllGenres() {
        return ResponseEntity.ok(webService.getAllGenres());
    }

    @GetMapping("/production-countries")
    public ResponseEntity<List<ProductionCountryDto>> getAllProductionCountries() {
        return ResponseEntity.ok(webService.getAllProductionCountries());
    }

    @GetMapping("/networks")
    public ResponseEntity<List<NetworkDto>> getAllNetworks() {
        return ResponseEntity.ok(webService.getAllNetworks());
    }

    @GetMapping("/languages")
    public ResponseEntity<List<LanguageDto>> getAllLanguages() {
        return ResponseEntity.ok(webService.getAllLanguages());
    }
    @PostMapping("/{seriesId}/genres")
    public ResponseEntity<SeriesDto> addGenreToSeries(@PathVariable Long seriesId, @RequestBody GenreDto genreDto) {
        return ResponseEntity.ok(webService.addGenreToSeries(seriesId, genreDto));
    }

    @PostMapping("/{seriesId}/languages")
    public ResponseEntity<SeriesDto> addLanguageToSeries(@PathVariable Long seriesId, @RequestBody LanguageDto languageDto) {
        return ResponseEntity.ok(webService.addLanguageToSeries(seriesId, languageDto));
    }

    @PostMapping("/{seriesId}/networks")
    public ResponseEntity<SeriesDto> addNetworkToSeries(@PathVariable Long seriesId, @RequestBody NetworkDto networkDto) {
        return ResponseEntity.ok(webService.addNetworkToSeries(seriesId, networkDto));
    }

    @PostMapping("/{seriesId}/production-countries")
    public ResponseEntity<SeriesDto> addProductionCountryToSeries(@PathVariable Long seriesId, @RequestBody ProductionCountryDto productionCountryDto) {
        return ResponseEntity.ok(webService.addProductionCountryToSeries(seriesId, productionCountryDto));
    }
}