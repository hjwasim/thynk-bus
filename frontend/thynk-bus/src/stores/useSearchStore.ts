import { create } from 'zustand';

interface SearchState {
    from: string,
    fromName: string,
    to: string,
    toName: string,
    setFrom: (from: string, fromName: string) => void,
    setTo: (to: string, toName: string) => void,
}

const useSearchStore = create<SearchState>()((set) => ({
    from: '',
    fromName: '',
    to: '',
    toName: '',
    date: '',
    setFrom: (from, fromName) => set((state) => ({ from, fromName })),
    setTo: (to, toName) => set((state) => ({ to, toName })),
}))


export default useSearchStore;