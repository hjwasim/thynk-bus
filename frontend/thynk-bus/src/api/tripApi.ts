import { TripSearchResponse, TripStageLayoutResponse } from "../models";

const BASE_TRIP_URL = 'http://192.168.1.3:8080/api/v1/trips';

export const searchTrips = async (from: string, to: string, date: string) => {

    const url = new URL(`${BASE_TRIP_URL}/search`)

    url.search = new URLSearchParams({
        from,
        to,
        date
    }).toString()

    console.log(url.toString());

    const res = await fetch(url)
    if (!res.ok) {
        throw new Error('Failed to fetch buses');
    }

    return res.json() as Promise<TripSearchResponse[]>
}


export const getCurrentSeatLayout = async (tripStageId: string) => {

    // const url = new URL(`${BASE_TRIP_URL}/stage/${tripStageId}/stage-layout`)

    // console.log(url.toString());

    // const res = await fetch(url)
    // if (!res.ok) {
    //     throw new Error('Failed to fetch bus seat layout');
    // }

    return Promise.resolve({

        "id": "697d01bd7aeca237a855650b",
        "upper": true,
        "rowMin": 1,
        "rowMax": 5,
        "colMin": 1,
        "colMax": 4,
        "seats": [
            {
                "code": "L1",
                "row": 1,
                "col": 1,
                "layer": 1,
                "aisle": false,
                "window": true,
                "price": 600.0
            },
            {
                "code": "",
                "row": 1,
                "col": 2,
                "layer": 1,
                "aisle": true,
                "window": false,
                "price": 600.0
            },
            {
                "code": "L2",
                "row": 1,
                "col": 3,
                "layer": 1,
                "aisle": false,
                "window": false,
                "price": 600.0
            },
            {
                "code": "L3",
                "row": 1,
                "col": 4,
                "layer": 1,
                "aisle": false,
                "window": true,
                "price": 600.0
            },
            {
                "code": "L4",
                "row": 2,
                "col": 1,
                "layer": 1,
                "aisle": false,
                "window": true,
                "price": 600.0
            },
            {
                "code": "",
                "row": 2,
                "col": 2,
                "layer": 1,
                "aisle": true,
                "window": false,
                "price": 600.0
            },
            {
                "code": "L5",
                "row": 2,
                "col": 3,
                "layer": 1,
                "aisle": false,
                "window": false,
                "price": 600.0
            },
            {
                "code": "L6",
                "row": 2,
                "col": 4,
                "layer": 1,
                "aisle": false,
                "window": true,
                "price": 600.0
            },
            {
                "code": "L7",
                "row": 3,
                "col": 1,
                "layer": 1,
                "aisle": false,
                "window": true,
                "price": 600.0
            },
            {
                "code": "",
                "row": 3,
                "col": 2,
                "layer": 1,
                "aisle": true,
                "window": false,
                "price": 600.0
            },
            {
                "code": "L8",
                "row": 3,
                "col": 3,
                "layer": 1,
                "aisle": false,
                "window": false,
                "price": 600.0
            },
            {
                "code": "L9",
                "row": 3,
                "col": 4,
                "layer": 1,
                "aisle": false,
                "window": true,
                "price": 600.0
            },
            {
                "code": "L10",
                "row": 4,
                "col": 1,
                "layer": 1,
                "aisle": false,
                "window": true,
                "price": 600.0
            },
            {
                "code": "",
                "row": 4,
                "col": 2,
                "layer": 1,
                "aisle": true,
                "window": false,
                "price": 600.0
            },
            {
                "code": "L11",
                "row": 4,
                "col": 3,
                "layer": 1,
                "aisle": false,
                "window": false,
                "price": 600.0
            },
            {
                "code": "L12",
                "row": 4,
                "col": 4,
                "layer": 1,
                "aisle": false,
                "window": true,
                "price": 600.0
            },
            {
                "code": "L13",
                "row": 5,
                "col": 1,
                "layer": 1,
                "aisle": false,
                "window": true,
                "price": 600.0
            },
            {
                "code": "",
                "row": 5,
                "col": 2,
                "layer": 1,
                "aisle": true,
                "window": false,
                "price": 600.0
            },
            {
                "code": "L14",
                "row": 5,
                "col": 3,
                "layer": 1,
                "aisle": false,
                "window": false,
                "price": 600.0
            },
            {
                "code": "L15",
                "row": 5,
                "col": 4,
                "layer": 1,
                "aisle": false,
                "window": true,
                "price": 600.0
            },
            {
                "code": "U1",
                "row": 1,
                "col": 1,
                "layer": 2,
                "aisle": false,
                "window": true,
                "price": 600.0
            },
            {
                "code": "",
                "row": 1,
                "col": 2,
                "layer": 2,
                "aisle": true,
                "window": false,
                "price": 600.0
            },
            {
                "code": "U2",
                "row": 1,
                "col": 3,
                "layer": 2,
                "aisle": false,
                "window": false,
                "price": 600.0
            },
            {
                "code": "U3",
                "row": 1,
                "col": 4,
                "layer": 2,
                "aisle": false,
                "window": true,
                "price": 600.0
            },
            {
                "code": "U4",
                "row": 2,
                "col": 1,
                "layer": 2,
                "aisle": false,
                "window": true,
                "price": 600.0
            },
            {
                "code": "",
                "row": 2,
                "col": 2,
                "layer": 2,
                "aisle": true,
                "window": false,
                "price": 600.0
            },
            {
                "code": "U5",
                "row": 2,
                "col": 3,
                "layer": 2,
                "aisle": false,
                "window": false,
                "price": 600.0
            },
            {
                "code": "U6",
                "row": 2,
                "col": 4,
                "layer": 2,
                "aisle": false,
                "window": true,
                "price": 600.0
            },
            {
                "code": "U7",
                "row": 3,
                "col": 1,
                "layer": 2,
                "aisle": false,
                "window": true,
                "price": 600.0
            },
            {
                "code": "",
                "row": 3,
                "col": 2,
                "layer": 2,
                "aisle": true,
                "window": false,
                "price": 600.0
            },
            {
                "code": "U8",
                "row": 3,
                "col": 3,
                "layer": 2,
                "aisle": false,
                "window": false,
                "price": 600.0
            },
            {
                "code": "U9",
                "row": 3,
                "col": 4,
                "layer": 2,
                "aisle": false,
                "window": true,
                "price": 600.0
            },
            {
                "code": "U10",
                "row": 4,
                "col": 1,
                "layer": 2,
                "aisle": false,
                "window": true,
                "price": 600.0
            },
            {
                "code": "",
                "row": 4,
                "col": 2,
                "layer": 2,
                "aisle": true,
                "window": false,
                "price": 600.0
            },
            {
                "code": "U11",
                "row": 4,
                "col": 3,
                "layer": 2,
                "aisle": false,
                "window": false,
                "price": 600.0
            },
            {
                "code": "U12",
                "row": 4,
                "col": 4,
                "layer": 2,
                "aisle": false,
                "window": true,
                "price": 600.0
            },
            {
                "code": "U13",
                "row": 5,
                "col": 1,
                "layer": 2,
                "aisle": false,
                "window": true,
                "price": 600.0
            },
            {
                "code": "",
                "row": 5,
                "col": 2,
                "layer": 2,
                "aisle": true,
                "window": false,
                "price": 600.0
            },
            {
                "code": "U14",
                "row": 5,
                "col": 3,
                "layer": 2,
                "aisle": false,
                "window": false,
                "price": 600.0
            },
            {
                "code": "U15",
                "row": 5,
                "col": 4,
                "layer": 2,
                "aisle": false,
                "window": true,
                "price": 600.0
            }
        ]

    }) as Promise<TripStageLayoutResponse>
    // return res.json() as Promise<TripStageLayoutResponse>
}