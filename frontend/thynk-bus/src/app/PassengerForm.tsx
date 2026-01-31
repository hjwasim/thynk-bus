import {
    View,
    Text,
    TextInput,
    Pressable,
    StyleSheet,
    TouchableOpacity,
    ScrollView,
} from 'react-native'
import React, { useEffect, useState } from 'react'
import { SafeAreaView } from 'react-native-safe-area-context'
import useSeatBookingStore from '../stores/useSeatBookingStore'
import Ionicons from '@expo/vector-icons/Ionicons'
import { router } from 'expo-router'
import { BookingSeat } from '../models'
import useTripStore from '../stores/useTripStore'
import { bookSeats } from '../api/bookingApi'

const PassengerForm = () => {
    const [results, setResults] = useState<BookingSeat[]>([])
    const { selectedSeats } = useSeatBookingStore()

    useEffect(() => {
        setResults(selectedSeats as BookingSeat[])
    }, [selectedSeats])

    const { tripStageId } = useTripStore()

    function updateForm(code: string, field: keyof BookingSeat, value: string) {
        setResults((prev) =>
            prev.map((seat) =>
                seat.code === code ? { ...seat, [field]: value } : seat
            )
        )
    }

    const submitBooking = () => {
        let isOK = results.every(
            (r) => r.passengerName && r.passengerMob && r.gender
        )

        if (isOK) {
            bookSeats({ bookingSeats: results, tripStageId })
            router.push('/BookedScreen')
        }
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

                    <View>
                        <Text style={styles.title}>
                            Enter Passengers Detail
                        </Text>
                    </View>
                </View>
            </View>

            <ScrollView>
                {results.map((seat) => (
                    <View style={styles.formInputContainer} key={seat.code}>
                        <Text style={{ fontFamily: 'Montserrat_600SemiBold' }}>
                            {seat.code}
                        </Text>

                        <View>
                            <TextInput
                                style={styles.formInput}
                                placeholder="Name"
                                onChangeText={(text) =>
                                    updateForm(seat.code, 'passengerName', text)
                                }
                            />
                            <TextInput
                                style={styles.formInput}
                                placeholder="Mobile Number"
                                keyboardType="phone-pad"
                                onChangeText={(text) =>
                                    updateForm(seat.code, 'passengerMob', text)
                                }
                            />
                            <View
                                style={{
                                    flexDirection: 'row',
                                    gap: 5,
                                    padding: 10,
                                }}
                            >
                                {['Male', 'Female'].map((g) => {
                                    const isActive = seat.gender === g

                                    return (
                                        <TouchableOpacity
                                            key={g}
                                            onPress={() =>
                                                updateForm(
                                                    seat.code,
                                                    'gender',
                                                    g
                                                )
                                            }
                                            style={[
                                                styles.genderBtn,
                                                isActive &&
                                                    styles.genderBtnActive,
                                            ]}
                                        >
                                            <Text
                                                style={[
                                                    styles.genderText,
                                                    isActive &&
                                                        styles.genderTextActive,
                                                ]}
                                            >
                                                {g}
                                            </Text>
                                        </TouchableOpacity>
                                    )
                                })}
                            </View>
                        </View>
                    </View>
                ))}
            </ScrollView>
            <Pressable style={styles.buttonContainer} onPressIn={submitBooking}>
                <Text style={styles.buttonText}>Book</Text>
            </Pressable>
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
        paddingVertical: 10,
    },
    formInputContainer: {
        flexDirection: 'row',
        alignItems: 'center',
        gap: 5,
        backgroundColor: '#fdf7ff',
        padding: 5,
        width: '95%',
        marginHorizontal: 'auto',
        marginVertical: 4,
    },
    formInput: {
        padding: 10,
        fontFamily: 'Montserrat_500Medium',
        fontSize: 15,
    },
    buttonContainer: {
        alignItems: 'center',
        backgroundColor: '#453cff',
        borderRadius: 5,
        marginVertical: 20,
        padding: 10,
        width: '95%',
        marginHorizontal: 'auto',
    },
    buttonText: {
        color: '#fdf7ff',
        fontFamily: 'Montserrat_600SemiBold',
        fontSize: 16,
    },
    genderBtn: {
        backgroundColor: '#dfdfdf',
        paddingVertical: 6,
        paddingHorizontal: 12,
        borderRadius: 6,
    },
    genderBtnActive: {
        backgroundColor: '#453cff',
    },
    genderText: {
        fontFamily: 'Montserrat_500Medium',
        color: '#000',
    },
    genderTextActive: {
        color: '#fff',
    },
})

export default PassengerForm
