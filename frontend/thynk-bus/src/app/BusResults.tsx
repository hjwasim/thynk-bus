import { View, Text, Pressable, StyleSheet } from 'react-native'
import React from 'react'
import { router, useLocalSearchParams } from 'expo-router'
import { useSuspenseQuery } from '@tanstack/react-query'
import { searchTrips } from '../api/tripApi'
import { SafeAreaView } from 'react-native-safe-area-context'
import Ionicons from '@expo/vector-icons/Ionicons'
import FontAwesome from '@expo/vector-icons/FontAwesome'
import Card from '../components/ui/Card'
import useTripStore from '../stores/useTripStore'

const formatDate = (date: Date): string => {
    return date.toLocaleTimeString('en-US', {
        hour: '2-digit',
        minute: '2-digit',
        hour12: true,
    })
}

const getMinutesBetweenDates = (startDate: Date, endDate: Date) => {
    const diffInMilliseconds = endDate.getTime() - startDate.getTime()
    const diffInMinutes = diffInMilliseconds / 60000
    return formatDecimalHours(Math.abs(diffInMinutes) / 60)
}

const formatDecimalHours = (decimalHours: number) => {
    let hours = Math.floor(decimalHours)
    let minutes = Math.round((decimalHours - hours) * 60)
    if (minutes >= 60) {
        hours += 1
        minutes = 0
    }
    let formattedMinutes = minutes < 10 ? '0' + minutes : minutes
    return `${hours} hr ${formattedMinutes} min`
}

const BusResults = () => {
    const {
        from,
        to,
        date,
        fromName,
        toName,
    }: {
        from: string
        to: string
        date: string
        fromName: string
        toName: string
    } = useLocalSearchParams()

    const { data } = useSuspenseQuery({
        queryKey: ['searchTrips'],
        queryFn: () => searchTrips(from, to, date),
    })

    const { setTripStageId } = useTripStore()

    const handleClick = (tripStageId: string) => {
        setTripStageId(tripStageId)
        router.push({
            pathname: '/SeatLayout',
            params: {
                tripStageId: tripStageId,
                fromName: fromName,
                toName: toName,
            },
        })
    }

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
                    <View style={styles.stationNameContainer}>
                        <Text style={styles.stationName}>{fromName}</Text>
                        <FontAwesome
                            name="long-arrow-right"
                            size={24}
                            color="#666"
                        />
                        <Text style={styles.stationName}>{toName}</Text>
                    </View>
                    {/* To make the station name center, just added for centering  */}
                    <Ionicons
                        style={{ opacity: 0 }}
                        name="arrow-back"
                        size={24}
                    />
                </View>
                <View>
                    {data.map((trip) => (
                        <Pressable
                            key={trip.id}
                            onPress={() => handleClick(trip.id)}
                        >
                            <Card
                                style={{
                                    marginHorizontal: 'auto',
                                    maxWidth: '95%',
                                    marginVertical: 10,
                                }}
                            >
                                <View style={{ flexDirection: 'row', gap: 6 }}>
                                    <View style={{ gap: 5, flex: 0.7 }}>
                                        <View
                                            style={{
                                                flexDirection: 'row',
                                                gap: 6,
                                            }}
                                        >
                                            <Text style={styles.duration}>
                                                {getMinutesBetweenDates(
                                                    new Date(
                                                        trip.departureTime
                                                    ),
                                                    new Date(trip.arrivalTime)
                                                )}
                                            </Text>
                                            <FontAwesome
                                                name="clock-o"
                                                size={16}
                                                color="#666"
                                            />
                                        </View>
                                        <View
                                            style={{
                                                flexDirection: 'row',
                                                gap: 6,
                                            }}
                                        >
                                            <Text style={styles.departureTime}>
                                                {formatDate(
                                                    new Date(trip.departureTime)
                                                )}
                                            </Text>
                                            <Text style={styles.arrivalTime}>
                                                -
                                            </Text>
                                            <Text style={styles.arrivalTime}>
                                                {formatDate(
                                                    new Date(trip.arrivalTime)
                                                )}
                                            </Text>
                                        </View>
                                        <View
                                            style={{
                                                flexDirection: 'row',
                                                gap: 5,
                                            }}
                                        >
                                            <Text style={styles.busName}>
                                                {trip.bus.name}
                                            </Text>
                                            <Ionicons
                                                name="bus-outline"
                                                size={22}
                                                color="#666"
                                            />
                                        </View>
                                        <Text style={styles.busType}>
                                            {trip.bus.type.name}
                                        </Text>
                                    </View>
                                    <View
                                        style={{
                                            justifyContent: 'center',
                                            margin: 'auto',
                                            paddingHorizontal: 10,
                                            borderLeftWidth: 1,
                                            borderLeftColor: '#666',
                                        }}
                                    >
                                        <Text style={styles.price}>
                                            &#8377;{trip.baseFare}
                                        </Text>
                                        <Text style={styles.busType}>
                                            Onwards
                                        </Text>
                                    </View>
                                </View>
                            </Card>
                        </Pressable>
                    ))}
                </View>
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
        shadowColor: '#333',
        shadowOpacity: 0.25,
        shadowRadius: 3,
        elevation: 2,
    },
    stationNameContainer: {
        padding: 10,
        flex: 1,
        flexDirection: 'row',
        alignItems: 'center',
        justifyContent: 'center',
        gap: 8,
    },
    stationName: {
        fontFamily: 'Montserrat_600SemiBold',
        fontSize: 16,
    },
    busName: {
        fontFamily: 'Montserrat_500Medium',
        fontSize: 14,
    },
    busType: {
        fontFamily: 'Montserrat_500Medium',
        fontSize: 12,
    },
    arrivalTime: {
        fontFamily: 'Montserrat_500Medium',
        fontSize: 16,
    },
    departureTime: {
        fontFamily: 'Montserrat_700Bold',
        fontSize: 16,
    },
    price: {
        fontFamily: 'Montserrat_700Bold',
        fontSize: 20,
    },
    duration: {
        fontFamily: 'Montserrat_500Medium',
        fontSize: 12,
    },
})

export default BusResults
