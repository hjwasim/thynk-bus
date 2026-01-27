package com.thynkbus.masterservice.dtos.route;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;

@Data
abstract public class BaseRouteRequest {

    @NotBlank(message = "From station ID is required")
    @Size(max = 20, message = "From station ID must not exceed 20 characters")
    private String fromStationId;

    @NotBlank(message = "To Station ID is required")
    @Size(max = 20, message = "To Station ID must not exceed 20 characters")
    private String toStationId;
}