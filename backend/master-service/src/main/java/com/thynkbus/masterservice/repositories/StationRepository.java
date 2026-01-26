package com.thynkbus.masterservice.repositories;

import com.thynkbus.masterservice.entities.Station;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StationRepository extends MongoRepository<Station, String> {
    Optional<Station> findByNameAndCityAndDistrict(String name, String city, String district);
}
