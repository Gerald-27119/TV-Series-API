package com.example.seriesupdater_.series.controllers;


import com.example.seriesupdater_.series.updater.ISeriesUpdater;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("updater/update")
public class UpdaterController {
    private final ISeriesUpdater seriesUpdater;

    public UpdaterController(ISeriesUpdater seriesUpdater) {
        this.seriesUpdater = seriesUpdater;
    }

    @GetMapping("popular")
    public ResponseEntity update(@RequestParam int firstPage, @RequestParam int lastPage) {
        new Thread(() -> seriesUpdater.updateByPopularity(firstPage,lastPage)).start();
        return ResponseEntity.ok().build();
    }
}
