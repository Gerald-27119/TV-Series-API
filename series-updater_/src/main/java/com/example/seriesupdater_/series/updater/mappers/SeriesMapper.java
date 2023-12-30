package com.example.seriesupdater_.series.updater.mappers;

import com.example.seriesclient.seriesclient.contract.SeriesDetailsDto;
import com.example.seriesdata.model.Series;
import com.example.seriesupdater_.series.updater.mappers.interfaces.IMap;
import org.springframework.stereotype.Component;

@Component
public class SeriesMapper implements IMap<SeriesDetailsDto, Series> {
    @Override
    public Series toEntity(SeriesDetailsDto dto) {//13 properties
        Series series = new Series();
        series.setSourceId(dto.getId());
        series.setInProduction(dto.isInProduction());
        series.setName(dto.getName());
        series.setNumberOfEpisodes(dto.getNumberOfEpisodes());
        series.setNumberOfSeasons(dto.getNumberOfSeasons());
        series.setOriginalName(dto.getOriginalName());
        String overview = dto.getOverview().trim().replaceAll("\\s{2,}", " ");
        if (overview.length() > 255) {
            overview = overview.substring(0, 255);
        }
        series.setOverview(overview);
        series.setPopularity(dto.getPopularity());
        series.setStatus(dto.getStatus());
        series.setAdult(dto.isAdult());
        series.setVoteAverage(dto.getVoteAverage());
        series.setVoteCount(dto.getVoteCount());
        series.setFirstAirDate(dto.getFirstAirDate());

        return series;
    }
}
