package com.thynkbus.masterservice.repositories;

import com.thynkbus.masterservice.entities.Trip;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface TripRepository extends MongoRepository<Trip, String> {

    Collection<Trip> findByScheduleId(String scheduleId);
}
