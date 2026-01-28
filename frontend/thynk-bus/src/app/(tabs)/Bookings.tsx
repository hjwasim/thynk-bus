import { View, Text } from 'react-native'
import React from 'react'
import { SafeAreaView } from 'react-native-safe-area-context'

const Bookings = () => {
    return (
        <SafeAreaView edges={['top']} style={{ flex: 1 }}>
            <Text>Bookings</Text>
        </SafeAreaView>
    )
}

export default Bookings
