import { SplashScreen, Stack } from 'expo-router'
import { useMontserratFont } from '../fonts/useMontserratFont'
import { StatusBar } from 'expo-status-bar'
import React from 'react'

SplashScreen.preventAutoHideAsync()

export default function RootLayout() {
    const [loaded, error] = useMontserratFont()
    React.useEffect(() => {
        if (loaded || error) {
            SplashScreen.hideAsync()
        }
    }, [loaded, error])

    if (!loaded && !error) {
        return null
    }
    return (
        <>
            <StatusBar style="dark" />
            <Stack>
                <Stack.Screen name="(tabs)" options={{ headerShown: false }} />
            </Stack>
        </>
    )
}
