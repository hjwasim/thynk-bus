package com.thynkbus.masterservice.repositories;

import com.thynkbus.masterservice.entities.TripStage;
import com.thynkbus.masterservice.entities.TripStageSeatLayout;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface TripStageSeatLayoutRepository extends MongoRepository<TripStageSeatLayout, String> {

}
