package com.thynkbus.masterservice.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
// effect only in output, NOT in DB
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Amenity {
    MOBILE_CHARGER("Mobile Charger"),
    LIVE_TRACKING("Live Tracking"),
    GPS_TRACKING("GPS Tracking"),
    WATTER_BOTTLE("Watter Bottle"),
    BEDSHEET("Bedsheet"),
    INBUILT_RESTROOM("Inbuilt Restroom"),
    LUXURY_FEEL("Luxury Feel");

    private final String name;

    Amenity(String name) {
        this.name = name;
    }
}
