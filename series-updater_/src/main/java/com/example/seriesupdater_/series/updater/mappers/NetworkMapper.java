package com.example.seriesupdater_.series.updater.mappers;

import com.example.seriesclient.seriesclient.contract.NetworkDto;
import com.example.seriesdata.model.Network;

import com.example.seriesupdater_.series.updater.mappers.interfaces.IMap;
import org.springframework.stereotype.Component;

@Component
public class NetworkMapper implements IMap<NetworkDto, Network> {
    @Override
    public Network toEntity(NetworkDto dto) {
        Network network = new Network();
        network.setName(dto.getName());
        network.setOriginCountry(dto.getOriginCountry());
        network.setLogoPath(dto.getLogoPath());

        return network;
    }
}
