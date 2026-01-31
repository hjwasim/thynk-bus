import { View, Text } from 'react-native'
import React, { useEffect } from 'react'
import { SafeAreaView } from 'react-native-safe-area-context'
import { LinearGradient } from 'expo-linear-gradient'
import MaterialIcons from '@expo/vector-icons/MaterialIcons'
import { router } from 'expo-router'

const BookedScreen = () => {
    useEffect(() => {
        const timer = setTimeout(() => {
            router.replace('/(tabs)/Home')
        }, 2000)

        return () => clearTimeout(timer)
    }, [])

    return (
        <SafeAreaView edges={['top']} style={{ flex: 1 }}>
            <LinearGradient
                start={{ x: 0, y: 0.2 }}
                colors={['#453cff', '#ed00c5', '#453cff']}
                style={{ flex: 1 }}
            >
                <View style={{ flex: 0.5 }}></View>
                <View
                    style={{
                        alignItems: 'center',
                        width: '80%',
                        marginHorizontal: 'auto',
                        justifyContent: 'center',
                    }}
                >
                    <MaterialIcons
                        name="done-outline"
                        size={64}
                        color="#fafafa"
                    />
                    <Text
                        style={{
                            fontSize: 36,
                            color: '#fafafa',
                            textAlign: 'center',
                            fontFamily: 'Montserrat_800ExtraBold',
                        }}
                    >
                        Ticket has been booked successfully!
                    </Text>
                </View>
            </LinearGradient>
        </SafeAreaView>
    )
}

export default BookedScreen
