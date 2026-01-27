package com.thynkbus.masterservice.repositories;

import com.thynkbus.masterservice.entities.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends MongoRepository<Schedule, String> {

    Optional<Schedule> findByRouteIdAndBusId(String routeId, String busId);
    Collection<Schedule> findAllByRouteId(String routeId);
}
