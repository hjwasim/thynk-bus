import { TripSearchResponse } from "../models";

const BASE_TRIP_URL = 'http://192.168.1.4:8080/api/v1/trips';

export const searchTrips = async (from: string, to: string, date: string) => {
    console.log(from, to, date);
    
    const url = new URL(`${BASE_TRIP_URL}`)

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