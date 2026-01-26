package com.thynkbus.masterservice.services;

import com.thynkbus.masterservice.dtos.station.StationCreateRequest;
import com.thynkbus.masterservice.dtos.station.StationResponse;
import com.thynkbus.masterservice.dtos.station.StationUpdateRequest;
import com.thynkbus.masterservice.entities.Station;
import com.thynkbus.masterservice.repositories.StationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StationService {

    protected final StationRepository stationRepository;
    protected final ModelMapper modelMapper;

    @Autowired
    public StationService(StationRepository stationRepository, ModelMapper modelMapper) {
        this.stationRepository = stationRepository;
        this.modelMapper = modelMapper;
    }

    public Collection<StationResponse> findAllStations() {
        Collection<Station> stations = stationRepository.findAll();
        return stations.stream()
                .map(station -> modelMapper.map(station, StationResponse.class))
                .collect(Collectors.toList());
    }

    public StationResponse findStationById(String id) {
        Optional<Station> station = stationRepository.findById(id);
        if (station.isEmpty()) {
            throw new RuntimeException("Station not found with id " + id);
        }
        return modelMapper.map(station.get(), StationResponse.class);
    }

    public StationResponse addStation(StationCreateRequest createRequest) {
        String name = createRequest.getName();
        String city = createRequest.getCity();
        String district = createRequest.getDistrict();

        Optional<Station> stationExist = stationRepository.findByNameAndCityAndDistrict(name, city, district);

        if (stationExist.isPresent()) {
            throw new RuntimeException("Station already exist with name " + name);
        }

        Station station = modelMapper.map(createRequest, Station.class);
        station = stationRepository.save(station);
        return modelMapper.map(station, StationResponse.class);
    }

    public StationResponse updateStation(String id, StationUpdateRequest updateRequest) {
        Optional<Station> station = stationRepository.findById(id);
        if (station.isEmpty()) {
            throw new RuntimeException("Station not found with id " + id);
        }
        modelMapper.map(updateRequest, station.get());
        Station updatedStation = stationRepository.save(station.get());
        return modelMapper.map(updatedStation, StationResponse.class);
    }

    public boolean deleteStationById(String id) {
        Optional<Station> station = stationRepository.findById(id);
        if (station.isEmpty()) {
            return false;
        }
        stationRepository.delete(station.get());
        return true;
    }
}
