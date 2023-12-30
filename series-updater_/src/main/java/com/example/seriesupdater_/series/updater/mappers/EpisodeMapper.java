package com.example.seriesupdater_.series.updater.mappers;

import com.example.seriesclient.seriesclient.contract.EpisodeDetailsDto;
import com.example.seriesdata.model.Episode;

import com.example.seriesupdater_.series.updater.mappers.interfaces.IMap;
import org.springframework.stereotype.Component;

@Component
public class EpisodeMapper implements IMap<EpisodeDetailsDto, Episode> {
    @Override
    public Episode toEntity(EpisodeDetailsDto dto) {
        Episode episode = new Episode();
        episode.setSourceId(dto.getId());
        episode.setName(dto.getName());
        String overview = dto.getOverview().trim().replaceAll("\\s{2,}", " ");
        if (overview.length() > 255) {
            overview = overview.substring(0, 255);
        }
        episode.setOverview(overview);
        episode.setAirDate(dto.getAirDate());
        episode.setSeasonNumber(dto.getSeasonNumber());
        episode.setEpisodeNumber(dto.getEpisodeNumber());
        episode.setRuntime(dto.getRuntime());
        episode.setVoteAverage(dto.getVoteAverage());
        episode.setVoteCount(dto.getVoteCount());
        episode.setStillPath(dto.getStillPath());
        episode.setProductionCode(dto.getProductionCode());

        return episode;
    }
}
