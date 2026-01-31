import { SeatBook, StationResponse } from "../models";

const BASE_BOOKING_URL = 'http://192.168.1.3:8080/api/v1/bookings';

export const bookSeats = async (seatBook: SeatBook) => {
    const res = await fetch(`${BASE_BOOKING_URL}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            tripStageId:seatBook.tripStageId,
            bookingSeats: seatBook.bookingSeats,
        }),
    });

    if (!res.ok) {
        throw new Error('Failed to fetch stations');
    }

    return res.json() as Promise<void>;
}