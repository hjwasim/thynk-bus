package com.thynkbus.masterservice.dtos.route;

import lombok.Data;

@Data
public class RouteResponse {

    private String fromStationId;
    private String toStationId;
    private String fromStationName;
    private String toStationName;
    private String routeId;
    private String routeName;
}
