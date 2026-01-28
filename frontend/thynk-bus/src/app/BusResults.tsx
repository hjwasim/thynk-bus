import { View, Text } from 'react-native'
import React from 'react'
import { useLocalSearchParams } from 'expo-router'
import { useSuspenseQuery } from '@tanstack/react-query'
import { searchTrips } from '../api/tripApi'
import { SafeAreaView } from 'react-native-safe-area-context'

const BusResults = () => {
    const { from, to, date }: { from: string; to: string; date: string } =
        useLocalSearchParams()

    const { data } = useSuspenseQuery({
        queryKey: ['searchTrips'],
        queryFn: () => searchTrips(from, to, date),
    })

    return (
        <SafeAreaView edges={['top']} style={{ flex: 1 }}>
            <Text>{JSON.stringify(data)}</Text>
        </SafeAreaView>
    )
}

export default BusResults
