package com.thynkbus.masterservice.dtos.station;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
abstract public class BaseStationRequest {

    // TODO - Add error description to the fields

    @NotBlank
    @Size(min = 1, max = 20)
    private String name;

    @NotBlank
    @Size(min = 1, max = 20)
    private String district;

    @NotBlank
    @Size(min = 1, max = 20)
    private String city;
}
