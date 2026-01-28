export type StationResponse = {
    id: string
    name: string
    city: string
    district: string
}

export type Amenity = {
    name: string
}

export type BusType = {
    name: string
    upperDeck: boolean
}

export type BusResponse = {
    id: string;
    name: string;
    ac: boolean;
    type: BusType;
    totalSeats: number
    active: boolean
    amenities: Amenity[]
}

export type RouteResponse = {
    fromStationId: string,
    toStationId: string,
    fromStationName: string,
    toStationName: string,
    routeId: string,
    routeName: string,
}
export type StationPoint = {
    stationId: string
    name: string
    address: string
    mobile: number
    time: string
    latitude: number,
    longitude: number
}

export type TripSearchResponse = {

    id: string
    bus: BusResponse
    route: RouteResponse
    fromStationId: string
    fromStationName: string
    toStationId: string
    toStationName: string
    seatLayoutId: string
    boardingPoints: StationPoint[]
    StationPoint: StationPoint[]
    departureTime: string
    arrivalTime: string
    baseFare: number
    status: string
    travelDate: string
}