package com.thynkbus.masterservice.entities;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "seat_layouts")
@Data
public class SeatLayout {

    @Id
    private String id;
    private String name;
    private Integer rowMin;
    private Integer rowMax;
    private Integer colMin;
    private Integer colMax;
    private List<Seat> seats;
}