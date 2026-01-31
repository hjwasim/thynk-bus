import { create } from 'zustand';
import { TripStageSeat } from '../models';

interface SeatBookingState {
    selectedSeats: TripStageSeat[],
    setSelectedSeatsStore: (selectedSeats: TripStageSeat[]) => void,
}

const useSeatBookingStore = create<SeatBookingState>()((set) => ({
    selectedSeats: [],
    setSelectedSeatsStore: (seats) => set({ selectedSeats: seats })
}))


export default useSeatBookingStore;