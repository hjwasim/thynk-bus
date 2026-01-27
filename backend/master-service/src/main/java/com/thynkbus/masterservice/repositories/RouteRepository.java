package com.thynkbus.masterservice.repositories;

import com.thynkbus.masterservice.entities.Route;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RouteRepository extends MongoRepository<Route, String> {

    Optional<Route> findByName(String name);
    Optional<Route> findByFromStationIdAndToStationId(String fromId, String toId);
}
