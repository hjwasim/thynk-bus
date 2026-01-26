package com.thynkbus.masterservice.repositories;

import com.thynkbus.masterservice.entities.SeatLayout;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SeatLayoutRepository extends MongoRepository<SeatLayout, String> {
    Optional<SeatLayout> findByName(String name);
}
