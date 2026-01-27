package com.thynkbus.masterservice.dtos.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

@Data
public class ScheduleCreateRequest {

    @NotBlank(message = "Route ID is required")
    @Size(max = 30, message = "Route ID must not exceed 20 characters")
    private String routeId;

    @NotBlank(message = "Bus code is required")
    @Size(max = 30, message = "Bus code must not exceed 20 characters")
    private String busId;

    @NotNull(message = "Departure time is required")
    private String departureTime;

    private Double startingFare;

    @NotNull(message = "Arrival time is required")
    private String arrivalTime;

    @NotEmpty(message = "At least one day must be provided")
    private Set<DayOfWeek> runningDays;

    @NotNull(message = "Start schedule date is required")
    private LocalDate startScheduleDate;

    @NotNull(message = "End schedule date is required")
    private LocalDate endScheduleDate;

}
