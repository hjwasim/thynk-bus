package com.thynkbus.masterservice.dtos.schedule;

import lombok.Data;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.util.Set;

@Data
public class ScheduleResponse {
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
