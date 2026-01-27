package com.thynkbus.masterservice.entities;

import com.thynkbus.masterservice.enums.Amenity;
import com.thynkbus.masterservice.enums.BusType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "buses")
@Data
public class Bus {

    @Id
    private String id;

    private String name;
    private Boolean ac;
    private BusType type;
    private Integer totalSeats;
    private Boolean active;
    private Set<Amenity> amenities;
    private String seatLayoutId;

}
