//package com.example.seriesclient;
//
//import com.example.seriesclient.seriesclient.*;
//import com.example.seriesclient.seriesclient.contract.EpisodeDetailsDto;
//import com.example.seriesclient.seriesclient.contract.PagedResultDto;
//import com.example.seriesclient.seriesclient.contract.SeasonDetailsDto;
//import com.example.seriesclient.seriesclient.contract.SeriesDetailsDto;
//
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.List;
//
//public class Main {
//    public static void main(String[] args) {
//        ISeriesClientSettings settings = new SeriesClientSettings("67cf6c481090cb3f6b929e68a8d601b0", "api.themoviedb.org", 3);
//        ISeriesClient seriesClient = new SeriesClient(settings);
//        ISeriesConfigurationClient seriesConfigurationClient = new SeriesConfigurationClient(settings);
//        var genres = seriesConfigurationClient.getGenres();
//        var languages = seriesConfigurationClient.getLanguages();
//        var countries = seriesConfigurationClient.getCountries();
//    }
//
//    public static void test(ISeriesClient seriesClient) {
//        // Get only the first page of popular series
//        PagedResultDto popularFirstPage = seriesClient.getPopularFirstPage();
//        var seriesSummaryDtoList = popularFirstPage.getResults();
//        // Get the next two pages of popular series
////        for (int page = 2; page <= 3; page++) {
////            seriesSummaryDtoList.addAll(seriesClient.getPopular(page).getResults());
////        }
//
//        var detailedSeriesDtoList = new ArrayList<SeriesDetailsDto>();
//        var detailedSeasonsDtoMap = new LinkedHashMap<List<SeasonDetailsDto>,String>();
//        var detailedEpisodesDtoMap = new LinkedHashMap<List<EpisodeDetailsDto>,String>();
//        var seasonsDetailedDtoList = new ArrayList<SeasonDetailsDto>();
//        var detailedEpisodesList = new ArrayList<EpisodeDetailsDto>();
//
//        for (var series : seriesSummaryDtoList) {
//            SeriesDetailsDto detailedSeries = seriesClient.getSeriesDetails(series.getId());
//            detailedSeriesDtoList.add(detailedSeries);
//
//            for (var season : detailedSeries.getSeasons()) {
//                SeasonDetailsDto detailedSeason = seriesClient.getSeasonDetails(detailedSeries.getId(), season.getSeasonNumber());
//                seasonsDetailedDtoList.add(detailedSeason);
//
//                for (var episode : detailedSeason.getEpisodes()) {
//                    EpisodeDetailsDto detailedEpisode = seriesClient.getEpisodeDetails(detailedSeries.getId(), detailedSeason.getSeasonNumber(), episode.getEpisodeNumber());
//                    detailedEpisodesList.add(detailedEpisode);
//                }
//                detailedEpisodesDtoMap.put(detailedEpisodesList, detailedSeason.getName());
//            }
//            detailedSeasonsDtoMap.put(seasonsDetailedDtoList, detailedSeries.getName());
//        }
//        System.out.println("Hi!");
//    }
//}
