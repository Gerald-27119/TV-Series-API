package com.example.seriesupdater_.series.updater.mappers.interfaces;

public interface IMap <TDto, TEntity>{
    TEntity toEntity(TDto dto);
}
