package com.thynkbus.masterservice.repositories;

import com.thynkbus.masterservice.entities.TripStage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface TripStageRepository extends MongoRepository<TripStage, String> {

    Optional<TripStage> findByTripId(String tripId);

    Collection<TripStage> findByFromStationIdAndToStationIdAndTravelDate(String fromStationId, String toStationId, String string);
}
