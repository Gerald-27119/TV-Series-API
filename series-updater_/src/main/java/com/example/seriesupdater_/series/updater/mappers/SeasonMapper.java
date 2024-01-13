package com.example.seriesupdater_.series.updater.mappers;

import com.example.seriesclient.seriesclient.contract.SeasonDetailsDto;
import com.example.seriesdata.model.Season;
import com.example.seriesupdater_.series.updater.mappers.interfaces.IMap;
import org.springframework.stereotype.Component;
@Component
public class SeasonMapper implements IMap<SeasonDetailsDto, Season> {
    @Override
    public Season toEntity(SeasonDetailsDto dto) {
        Season season = new Season();
        season.setSourceId(dto.getId());
        season.setAirDate(dto.getAirDate());
        season.setName(dto.getName());
        String overview = dto.getOverview().trim().replaceAll("\\s{2,}", " ");
        if (overview.length() > 255) {
            overview = overview.substring(0, 255);
        }
        season.setOverview(overview);
        season.setPosterPath(dto.getPosterPath());
        season.setSeasonNumber(dto.getSeasonNumber());
        season.setVoteAverage(dto.getVoteAverage());
        return season;
    }
}
