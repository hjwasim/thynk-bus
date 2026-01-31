import { create } from 'zustand';

interface TripState {

    fromName: string,
    toName: string,
    tripStageId: string,
    setFromName: (fromName: string) => void,
    setToName: (toName: string) => void,
    setTripStageId: (tripStageId: string) => void,
}

const useTripStore = create<TripState>()((set) => ({
    fromName: '',
    toName: '',
    tripStageId: '',
    setFromName: (fromName) => set((state) => ({ fromName })),
    setToName: (toName) => set((state) => ({ toName })),
    setTripStageId: (tripStageId) => set((state) => ({ tripStageId })),

}))


export default useTripStore;