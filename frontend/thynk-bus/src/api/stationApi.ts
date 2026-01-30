import { StationResponse } from "../models";

const BASE_STATION_URL = 'http://192.168.1.3:8080/api/v1/stations';

export const getAllStations = async () => {
    const res = await fetch(`${BASE_STATION_URL}`);
    
    if (!res.ok) {
        throw new Error('Failed to fetch stations');
    }

    return res.json() as Promise<StationResponse[]>;
}