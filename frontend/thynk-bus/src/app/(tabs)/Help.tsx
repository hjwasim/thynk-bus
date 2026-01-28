import { View, Text } from 'react-native'
import React from 'react'
import { SafeAreaView } from 'react-native-safe-area-context'

const Help = () => {
    return (
        <SafeAreaView edges={['top']} style={{ flex: 1 }}>
            <Text>Help</Text>
        </SafeAreaView>
    )
}

export default Help
