package com.thynkbus.masterservice.dtos.bus;

import com.thynkbus.masterservice.enums.Amenity;
import com.thynkbus.masterservice.enums.BusType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.Set;

@Getter
abstract public class BaseBusRequest {

    // TODO - Add error description to the fields

    @NotBlank
    @Size(min = 1, max = 20)
    private String name;

    @NotNull
    private Boolean ac;

    @NotNull
    private BusType type;

    @NotNull
    @Min(1)
    // TODO - need to define min and max for total seats
    private Integer totalSeats;

    @NotNull
    // bus should have at-least one amenity
    private Set<Amenity> amenities;
}
