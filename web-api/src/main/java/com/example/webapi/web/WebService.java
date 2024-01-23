package com.example.webapi.web;

import com.example.seriesdata.ICatalogData;
import com.example.seriesdata.model.*;
import com.example.webapi.dtos.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WebService {

    private final ICatalogData catalogData;
    @PersistenceContext
    private EntityManager entityManager;

    public List<SeriesDto> getAll() {
        return catalogData.getSeries().findAll().stream().map(this::mapSeriesToDto).collect(Collectors.toList());
    }
    public SeriesDto getSeriesById(Long id) {
        return mapSeriesToDto(catalogData.getSeries().findById(id).orElseThrow(InvalidParameterException::new));
    }
    @Transactional
    public SeriesDto createSeries(SeriesDto seriesDto) {
        Series series = new Series();

        series.setName(seriesDto.name());
        series.setAdult(seriesDto.adult());
        series.setInProduction(seriesDto.inProduction());
        series.setNumberOfSeasons(seriesDto.numberOfSeasons());
        series.setNumberOfEpisodes(seriesDto.numberOfEpisodes());
        series.setVoteAverage(seriesDto.voteAverage());
        series.setPopularity(seriesDto.popularity());
        series.setVoteCount(seriesDto.voteCount());
        series.setFirstAirDate(seriesDto.firstAirDate());
        series.setStatus(seriesDto.status());

        catalogData.getSeries().save(series);

        return mapSeriesToDto(series);
    }
    @Transactional
    public void deleteSeries(Long seriesId) {
        catalogData.getSeries().findById(seriesId).orElseThrow(InvalidParameterException::new);

        entityManager.createNativeQuery("DELETE FROM genre_series WHERE genre_series.series_id = :seriesId")
                .setParameter("seriesId", seriesId)
                .executeUpdate();

        entityManager.createNativeQuery("DELETE FROM language_series WHERE language_series.series_id = :seriesId")
                .setParameter("seriesId", seriesId)
                .executeUpdate();

        entityManager.createNativeQuery("DELETE FROM production_country_series WHERE production_country_series.series_id = :seriesId")
                .setParameter("seriesId", seriesId)
                .executeUpdate();

        entityManager.createNativeQuery("DELETE FROM network_series WHERE network_series.series_id = :seriesId")
                .setParameter("seriesId", seriesId)
                .executeUpdate();
        entityManager.createNativeQuery("DELETE FROM episode WHERE episode.season_id IN (SELECT id FROM season WHERE season.series_id = :seriesId)")
                .setParameter("seriesId", seriesId)
                .executeUpdate();
        entityManager.createNativeQuery("DELETE FROM season WHERE season.series_id = :seriesId")
                .setParameter("seriesId", seriesId)
                .executeUpdate();

        entityManager.createNativeQuery("DELETE FROM series WHERE series.id = :seriesId")
                .setParameter("seriesId", seriesId)
                .executeUpdate();
    }
    @Transactional
    public SeriesDto putSeries(Long id, SeriesDto seriesDto) {
        Series series = catalogData.getSeries().findById(id)
                .orElseThrow(InvalidParameterException::new);

        series.setName(seriesDto.name());
        series.setAdult(seriesDto.adult());
        series.setInProduction(seriesDto.inProduction());
        series.setNumberOfSeasons(seriesDto.numberOfSeasons());
        series.setNumberOfEpisodes(seriesDto.numberOfEpisodes());
        series.setVoteAverage(seriesDto.voteAverage());
        series.setPopularity(seriesDto.popularity());
        series.setVoteCount(seriesDto.voteCount());
        series.setFirstAirDate(seriesDto.firstAirDate());
        series.setStatus(seriesDto.status());

        catalogData.getSeries().save(series);

        return mapSeriesToDto(series);
    }
    public List<GenreDto> getAllGenres() {
        return catalogData.getGenres().findAll().stream()
                .map(genre -> new GenreDto(genre.getName()))
                .collect(Collectors.toList());
    }
    public List<ProductionCountryDto> getAllProductionCountries() {
        return catalogData.getProductionCountries().findAll().stream()
                .map(productionCountry -> new ProductionCountryDto(productionCountry.getName()))
                .collect(Collectors.toList());
    }
    public List<NetworkDto> getAllNetworks() {
        return catalogData.getNetworks().findAll().stream()
                .map(network -> new NetworkDto(
                        network.getName(),
                        network.getLogoPath(),
                        network.getOriginCountry()
                ))
                .collect(Collectors.toList());
    }
    public List<LanguageDto> getAllLanguages() {
        return catalogData.getLanguages().findAll().stream()
                .map(language -> new LanguageDto(language.getNativeName(), language.getEnglishName()))
                .collect(Collectors.toList());
    }
    @Transactional
    public SeriesDto addGenreToSeries(Long seriesId, GenreDto genreDto) {
        Series series = catalogData.getSeries().findById(seriesId)
                .orElseThrow(InvalidParameterException::new);
        if(catalogData.getGenres().existsByName(genreDto.name()))
        {
            Genre genre = catalogData.getGenres().findTopByName(genreDto.name());
            genre.getSeries().add(series);
            series.getGenres().add(genre);
            catalogData.getSeries().save(series);
        }
        else throw new InvalidParameterException();
        return mapSeriesToDto(series);
    }

    @Transactional
    public SeriesDto addLanguageToSeries(Long seriesId, LanguageDto languageDto) {
        Series series = catalogData.getSeries().findById(seriesId)
                .orElseThrow(InvalidParameterException::new);
        if(catalogData.getLanguages().existsByEnglishName(languageDto.englishName())) {
            Language language = catalogData.getLanguages().findTopByEnglishName(languageDto.englishName());
            language.getSeries().add(series);
            series.getLanguages().add(language);
            catalogData.getSeries().save(series);
        } else {
            throw new InvalidParameterException();
        }
        return mapSeriesToDto(series);
    }

    @Transactional
    public SeriesDto addNetworkToSeries(Long seriesId, NetworkDto networkDto) {
        Series series = catalogData.getSeries().findById(seriesId)
                .orElseThrow(InvalidParameterException::new);
        if(catalogData.getNetworks().existsByName(networkDto.name())) {
            Network network = catalogData.getNetworks().findTopByName(networkDto.name());
            network.getSeries().add(series);
            series.getNetworks().add(network);
            catalogData.getSeries().save(series);
        } else {
            throw new InvalidParameterException();
        }
        return mapSeriesToDto(series);
    }

    @Transactional
    public SeriesDto addProductionCountryToSeries(Long seriesId, ProductionCountryDto productionCountryDto) {
        Series series = catalogData.getSeries().findById(seriesId)
                .orElseThrow(InvalidParameterException::new);
        if(catalogData.getProductionCountries().existsByName(productionCountryDto.name())) {
            ProductionCountry productionCountry = catalogData.getProductionCountries().findTopByName(productionCountryDto.name());
            productionCountry.getSeries().add(series);
            series.getProductionCountries().add(productionCountry);
            catalogData.getSeries().save(series);
        } else {
            throw new InvalidParameterException();
        }
        return mapSeriesToDto(series);
    }
    private SeriesDto mapSeriesToDto(Series series){
        return new SeriesDto(
                series.getId(),
                series.getName(),
                series.isAdult(),
                series.isInProduction(),
                series.getNumberOfSeasons(),
                series.getNumberOfEpisodes(),
                series.getVoteAverage(),
                series.getPopularity(),
                series.getVoteCount(),
                series.getFirstAirDate(),
                series.getStatus(),

                series.getGenres().stream().map(genre -> new GenreDto(genre.getName()))
                        .collect(Collectors.toList()),

                series.getNetworks().stream().map(network -> new NetworkDto(
                        network.getName(),
                        network.getLogoPath(),
                        network.getOriginCountry()
                )).collect(Collectors.toList()),

                series.getProductionCountries().stream().map(productionCountry -> new ProductionCountryDto(
                        productionCountry.getName())).collect(Collectors.toList()),

                series.getSeasons().stream().map(season -> new SeasonDto(
                        season.getName(),
                        season.getOverview(),
                        season.getPosterPath(),
                        season.getSeasonNumber(),
                        season.getVoteAverage(),
                        season.getAirDate(),
                        season.getEpisodes().stream().map(episode -> new EpisodeDto(
                                episode.getName(),
                                episode.getAirDate(),
                                episode.getSeasonNumber(),
                                episode.getRuntime()
                        )).collect(Collectors.toList())

                )).collect(Collectors.toList()),

                series.getLanguages().stream().map(language -> new LanguageDto(language.getNativeName(), language.getEnglishName()))
                        .collect(Collectors.toList())
        );
    }
}
