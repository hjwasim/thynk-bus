import {
    Alert,
    Platform,
    Pressable,
    StyleSheet,
    Text,
    TextInput,
    View,
} from 'react-native'
import React, { useState } from 'react'
import DateTimePicker, {
    DateTimePickerEvent,
} from '@react-native-community/datetimepicker'
import Ionicons from '@expo/vector-icons/Ionicons'
import Fontisto from '@expo/vector-icons/Fontisto'
import Card from '../ui/Card'
import { router } from 'expo-router'
import useSearchStore from '@/src/stores/useSearchStore'

const formatDate = (date: Date): string => {
    return date.toLocaleDateString('en-GB', {
        dateStyle: 'long',
    })
}

const getFormattedDateForSearch = (date: Date): string => {
    const year = date.getFullYear()
    const month = String(date.getMonth() + 1).padStart(2, '0')
    const day = String(date.getDate()).padStart(2, '0')
    return `${year}-${month}-${day}`
}

const today = new Date()
const maxDate = new Date()
maxDate.setDate(today.getDate() + 10)

const TripSearchForm = () => {
    const [showDatePicker, setShowDatePicker] = useState<boolean>(false)
    const [date, setDate] = useState<Date>(new Date())
    const { from, fromName, to, toName } = useSearchStore()

    const handlePress = () => {
        if (!fromName || !from) {
            Alert.alert('Missing Source', 'Please select your source location.')
            return
        }

        if (!toName || !to) {
            Alert.alert(
                'Missing Destination',
                'Please select your destination.'
            )
            return
        }

        if (!date) {
            Alert.alert('Missing Date', 'Please select a travel date.')
            return
        }

        if (fromName === toName) {
            Alert.alert(
                'Invalid Route',
                'Source and destination cannot be the same.'
            )
            return
        }

        router.push({
            pathname: '/BusResults',
            params: { from, to, date: getFormattedDateForSearch(date) },
        })

    }

    const chooseDate = (
        event: DateTimePickerEvent,
        selectedDate: Date | undefined
    ) => {
        setShowDatePicker(Platform.OS === 'ios')
        if (selectedDate) setDate(selectedDate)
    }

    return (
        <Card style={{ marginVertical: 12 }}>
            <View>
                <View style={styles.stationInputContainer}>
                    <Ionicons
                        name="location-outline"
                        style={{ marginLeft: 5 }}
                        size={24}
                        color="#333"
                    />
                    <TextInput
                        style={styles.stationInput}
                        value={fromName}
                        placeholder="Choose your source place"
                        onFocus={() =>
                            router.push({
                                pathname: '/LocationSearch',
                                params: { from: 'from' },
                            })
                        }
                    />
                </View>
                <View style={styles.stationInputContainer}>
                    <Ionicons
                        name="location-outline"
                        style={{ marginLeft: 5 }}
                        size={24}
                        color="#333"
                    />
                    <TextInput
                        style={styles.stationInput}
                        placeholder="Where are you going to ?"
                        value={toName}
                        onFocus={() =>
                            router.push({
                                pathname: '/LocationSearch',
                                params: { from: 'to' },
                            })
                        }
                    />
                </View>
                <View style={styles.stationInputContainer}>
                    <Fontisto
                        name="date"
                        style={{ marginLeft: 5 }}
                        size={24}
                        color="#333"
                    />
                    <Pressable
                        style={{ width: '100%' }}
                        onPress={() => setShowDatePicker(true)}
                    >
                        <Text style={styles.stationInput}>
                            {formatDate(date)}
                        </Text>
                    </Pressable>
                    {showDatePicker && (
                        <DateTimePicker
                            value={date}
                            mode="date"
                            display={
                                Platform.OS === 'ios' ? 'inline' : 'calendar'
                            }
                            minimumDate={today}
                            maximumDate={maxDate}
                            onChange={chooseDate}
                        />
                    )}
                </View>

                <Pressable
                    style={styles.buttonContainer}
                    onPressIn={handlePress}
                >
                    <Text style={styles.buttonText}>Search</Text>
                </Pressable>
            </View>
        </Card>
    )
}

const styles = StyleSheet.create({
    stationInputContainer: {
        flexDirection: 'row',
        alignItems: 'center',
        gap: 5,
        backgroundColor: '#fdf7ff',
        padding: 5,
        marginVertical: 4,
    },
    stationInput: {
        padding: 10,
        fontFamily: 'Montserrat_500Medium',
        fontSize: 15,
    },
    buttonContainer: {
        alignItems: 'center',
        backgroundColor: '#453cff',
        borderRadius: 5,
        marginVertical: 14,
        padding: 10,
    },
    buttonText: {
        color: '#fdf7ff',
        fontFamily: 'Montserrat_500Medium',
        fontSize: 15,
    },
})

export default TripSearchForm
