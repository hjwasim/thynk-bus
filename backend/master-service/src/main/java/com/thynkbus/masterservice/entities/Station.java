package com.thynkbus.masterservice.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Station {
    @Id
    private String id;
    private String name;
    private String city;
    private String district;
}
