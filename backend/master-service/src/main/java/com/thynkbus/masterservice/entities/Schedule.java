package com.thynkbus.masterservice.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.util.Set;

@Document(collection = "schedules")
@CompoundIndex(
        name = "unique_schedule_idx",
        def = "{'routeId':1,'busId':1,'departureTime':1}",
        unique = true
)
@Data
public class Schedule {

    @Id
    private String id;
    private String routeId;
    private String busId;

    private String departureTime;
    private String arrivalTime;

    private Double startingFare;

    private Set<DayOfWeek> runningDays;

    private String startScheduleDate;
    private String endScheduleDate;

    private boolean active;
}
