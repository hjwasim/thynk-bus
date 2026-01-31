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

export type TripStageSeat = {
    sold: boolean
    code: string
    row: number
    col: number
    layer: number
    aisle: boolean
    window: boolean
    price: number
}

export type TripStageLayoutResponse = {
    id: string
    upper: boolean
    rowMin: number
    rowMax: number
    colMin: number
    colMax: number
    seats: TripStageSeat[]
}

export type BookingSeat = {
    sold: boolean
    code: string
    row: number
    col: number
    layer: number
    aisle: boolean
    window: boolean
    price: number
    passengerName: string
    passengerMob: string
    gender: string
    status: string
}

export type SeatBook = {
    tripStageId: string
    bookingSeats: BookingSeat[]
}