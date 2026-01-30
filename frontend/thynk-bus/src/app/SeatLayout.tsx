import { View, Text, StyleSheet, Pressable, ScrollView } from 'react-native'
import React, { useState } from 'react'
import { SafeAreaView } from 'react-native-safe-area-context'
import { router, useLocalSearchParams } from 'expo-router'
import { useSuspenseQuery } from '@tanstack/react-query'
import { getCurrentSeatLayout } from '../api/tripApi'
import Ionicons from '@expo/vector-icons/Ionicons'
import FontAwesome from '@expo/vector-icons/FontAwesome'
import { TripStageSeat } from '../models'
import { VerticalSleeper } from '../components/core/VerticalSleeper'
import MaterialCommunityIcons from '@expo/vector-icons/MaterialCommunityIcons'

export const buildVerticalGridByLayer = (
    data: TripStageSeat[]
): Record<number, TripStageSeat[][]> => {
    const layers = data.reduce<Record<number, TripStageSeat[]>>((acc, seat) => {
        acc[seat.layer] ??= []
        acc[seat.layer].push(seat)
        return acc
    }, {})

    const result: Record<number, TripStageSeat[][]> = {}

    Object.keys(layers).forEach((layerKey) => {
        const layer = Number(layerKey)

        const cols = layers[layer].reduce<Record<number, TripStageSeat[]>>(
            (acc, seat) => {
                acc[seat.col] ??= []
                acc[seat.col].push(seat)
                return acc
            },
            {}
        )

        result[layer] = Object.keys(cols)
            .map(Number)
            .sort((a, b) => a - b)
            .map((col) => cols[col].sort((a, b) => a.row - b.row))
    })

    return result
}

const SeatLayout = () => {
    const {
        tripStageId,
        fromName,
        toName,
    }: { tripStageId: string; fromName: string; toName: string } =
        useLocalSearchParams()
    const { data } = useSuspenseQuery({
        queryKey: ['seatLayout'],
        queryFn: () => getCurrentSeatLayout(tripStageId),
    })
    const [selectedSeats, setSelectedSeats] = useState<TripStageSeat[]>([])

    const { seats, upper } = data

    const grids = buildVerticalGridByLayer(seats)
    const lowerDeck = grids[1]
    let upperDeck
    if (upper) {
        upperDeck = grids[2]
    }
    const onSeatPress = (seat: TripStageSeat) => {
        if(selectedSeats.length > 6) return
        if (seat.aisle || seat.code === '' || seat.sold) return

        setSelectedSeats((prev: TripStageSeat[]) => {
            const exists = prev.find((s) => s.code === seat.code)

            if (exists) {
                return prev.filter((s) => s.code !== seat.code)
            }

            // select
            return [...prev, seat]
        })
    }
    const isSelected = (seat: TripStageSeat) =>
        selectedSeats.some((s: TripStageSeat) => s.code === seat.code)

    return (
        <SafeAreaView edges={['top']} style={{ flex: 1 }}>
            <View>
                <View style={styles.headerContainer}>
                    <Pressable onPress={() => router.back()}>
                        <Ionicons
                            style={{ marginLeft: 5 }}
                            name="arrow-back"
                            size={24}
                        />
                    </Pressable>

                    <View>
                        <Text style={styles.title}>Select Seats</Text>
                        <View style={styles.stationNameContainer}>
                            <Text style={styles.stationName}>{fromName}</Text>
                            <FontAwesome
                                name="long-arrow-right"
                                size={24}
                                color="#666"
                            />
                            <Text style={styles.stationName}>{toName}</Text>
                        </View>
                    </View>
                </View>
            </View>

            <View style={styles.seatLayoutWrapper}>
                <ScrollView
                    horizontal
                    showsHorizontalScrollIndicator={false}
                    contentContainerStyle={styles.horizontalScroll}
                >
                    {/* LOWER DECK */}
                    <View style={styles.deckContainer}>
                        <View
                            style={{
                                flexDirection: 'row',
                                justifyContent: 'space-around',
                                marginVertical: 10,
                            }}
                        >
                            <Text style={styles.deckTitle}>Lower deck</Text>
                            <MaterialCommunityIcons
                                name="steering"
                                size={40}
                                color="#666"
                            />
                        </View>
                        <ScrollView showsVerticalScrollIndicator={false}>
                            <View style={styles.deck}>
                                {lowerDeck.map((column, colIndex) => (
                                    <View key={colIndex} style={styles.column}>
                                        {column.map((seat, rowIndex) => (
                                            <VerticalSleeper
                                                key={`${seat.code}-${rowIndex}`}
                                                seat={seat}
                                                selected={isSelected(seat)}
                                                onPress={onSeatPress}
                                            />
                                        ))}
                                    </View>
                                ))}
                            </View>
                        </ScrollView>
                    </View>

                    <View style={{ width: 15 }}></View>
                    {/* UPPER DECK */}
                    {upperDeck && (
                        <View style={styles.deckContainer}>
                            <View
                                style={{
                                    flexDirection: 'row',
                                    justifyContent: 'space-around',
                                    marginVertical: 10,
                                }}
                            >
                                <Text style={styles.deckTitle}>Upper deck</Text>
                            </View>
                            <ScrollView showsVerticalScrollIndicator={false}>
                                <View style={styles.deck}>
                                    {grids[2].map((column, colIndex) => (
                                        <View
                                            key={colIndex}
                                            style={styles.column}
                                        >
                                            {column.map((seat, rowIndex) => (
                                                <VerticalSleeper
                                                    key={`${seat.code}-${rowIndex}`}
                                                    seat={seat}
                                                    selected={isSelected(seat)}
                                                    onPress={onSeatPress}
                                                />
                                            ))}
                                        </View>
                                    ))}
                                </View>
                            </ScrollView>
                        </View>
                    )}
                </ScrollView>
            </View>
        </SafeAreaView>
    )
}

const styles = StyleSheet.create({
    headerContainer: {
        flexDirection: 'row',
        alignItems: 'center',
        backgroundColor: '#fdf7ff',
        padding: 10,
        marginVertical: 5,
        gap: 15,
        shadowColor: '#333',
        shadowOpacity: 0.25,
        shadowRadius: 3,
        elevation: 2,
    },
    stationNameContainer: {
        flexDirection: 'row',
        alignItems: 'center',
        gap: 8,
    },
    stationName: {
        fontFamily: 'Montserrat_500Medium',
        fontSize: 14,
    },
    title: {
        fontFamily: 'Montserrat_600SemiBold',
        fontSize: 16,
    },
    seatLayoutWrapper: {
        width: '95%',
        flexDirection: 'row',
        justifyContent: 'center',
        marginHorizontal: 'auto',
        backgroundColor: '#fdf7ff',
        paddingVertical: 15,
        borderRadius: 6,
    },
    scrollContent: {
        paddingHorizontal: 12,
    },
    horizontalScroll: {
        paddingHorizontal: 12,
    },
    deckContainer: {
        backgroundColor: '#fff',
        borderRadius: 16,
        padding: 12,
    },
    deckTitle: {
        fontSize: 14,
        marginBottom: 15,
        fontFamily: 'Montserrat_600SemiBold',
    },

    deck: {
        flexDirection: 'row',
    },

    column: {
        flexDirection: 'column',
        marginRight: 12,
    },
})

export default SeatLayout
