import TripSearchForm from '@/src/components/core/TripSearchForm'
import { LinearGradient } from 'expo-linear-gradient'
import React from 'react'
import { StyleSheet, Text, View } from 'react-native'
import { SafeAreaView } from 'react-native-safe-area-context'

const Home = () => {
    return (
        <SafeAreaView edges={['top']} style={{ flex: 1 }}>
            <LinearGradient
                start={{ x: 0, y: 0.2 }}
                colors={['#453cff', '#ed00c5', '#453cff']}
                style={{ flex: 1 }}
            ></LinearGradient>
            <View style={{ flex: 2, backgroundColor: '#fdf7ff' }}></View>
            <View
                style={{
                    position: 'absolute',
                    width: '100%',
                    top: '15%',
                    alignItems: 'center',
                }}
            >
                <View style={{ width: '90%' }}>
                    <Text style={styles.welcomeText}>
                        Lets Book your Bus Ticket!
                    </Text>
                    <Text style={styles.descriptionText}>
                        Save time and travel comfortably with ThynkBus
                    </Text>
                    <TripSearchForm />
                </View>
            </View>
        </SafeAreaView>
    )
}

const styles = StyleSheet.create({
    welcomeText: {
        fontFamily: 'Montserrat_700Bold',
        fontSize: 28,
        color: '#fdf7ff',
    },
    descriptionText: {
        fontFamily: 'Montserrat_500Medium',
        fontSize: 16,
        color: '#fff',
    },
})

export default Home
