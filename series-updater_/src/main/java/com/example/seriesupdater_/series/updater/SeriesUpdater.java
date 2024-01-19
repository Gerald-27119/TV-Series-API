package com.example.seriesupdater_.series.updater;

import com.example.seriesclient.seriesclient.ISeriesClient;
import com.example.seriesclient.seriesclient.contract.SeriesDetailsDto;
import com.example.seriesclient.seriesclient.contract.SeriesSummaryDto;
import com.example.seriesdata.ICatalogData;
import com.example.seriesdata.model.Series;
import com.example.seriesupdater_.series.updater.mappers.interfaces.IMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class SeriesUpdater implements ISeriesUpdater {
    private final IMapper mapper;
    private final ISeriesClient client;
    private final ICatalogData dbCatalog;
    private final TransactionTemplate transactionTemplate;
    private final SeriesComponent seriesComponent;
    private final ConfigurationComponent configurationComponent;
    private int totalSeries;
    private AtomicInteger processedSeries = new AtomicInteger();
    private static final Logger log = LoggerFactory.getLogger(SeriesUpdater.class);

    @Override
    public void updateByPopularity(int firstPage, int lastPage) {
        totalSeries = (lastPage - firstPage + 1) * 20;
        log.info("Starting update by popularity for pages from " + firstPage + " to " + lastPage + "...");

        log.info("Starting configuration update...");
        configurationComponent.updateConfiguration();
        log.info("Finished updating configuration.");
        log.info("Starting updating series...");


        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (int i = firstPage; i <= lastPage; i++) {
            client.getPopular(i).getResults().forEach(seriesSummaryDto -> {
                CompletableFuture<Void> seriesFuture = CompletableFuture.runAsync(() -> {
                    transactionTemplate.executeWithoutResult(status -> {
                        var sourceId = seriesSummaryDto.getId();
                        Series series = dbCatalog.getSeries().findBySourceId(sourceId).orElse(null);

                        if (series == null) {
                            saveSeries(seriesSummaryDto);
                        } else {
                            log.info("Series with source ID = {} already exists in database", sourceId);
                        }
                    });
                });
                futures.add(seriesFuture);


            });
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        log.info("Finished update by popularity.");
    }
    public void saveSeries(SeriesSummaryDto seriesSummaryDto) {
        SeriesDetailsDto seriesDetailsDto = client.getSeriesDetails(seriesSummaryDto.getId());
        Series series = mapper.series().toEntity(seriesDetailsDto);

        seriesComponent.saveLanguages(seriesDetailsDto, series);
        seriesComponent.saveGenres(seriesDetailsDto, series);
        seriesComponent.saveProductionCountries(seriesDetailsDto, series);
        seriesComponent.saveNetwork(seriesDetailsDto, series);
        seriesComponent.saveEpisodesAndSeasons(seriesDetailsDto, series);

        dbCatalog.getSeries().save(series);
        processedSeries.getAndIncrement();
        log.info("Series with source ID = {} processed successfully. Remaining series: {}", series.getSourceId(), totalSeries - processedSeries.get());
    }

}
