import { SplashScreen, Stack } from 'expo-router'
import { useMontserratFont } from '../fonts/useMontserratFont'
import { StatusBar } from 'expo-status-bar'
import React from 'react'
import { QueryClient, QueryClientProvider } from '@tanstack/react-query'

SplashScreen.preventAutoHideAsync()

const queryClient = new QueryClient({
    defaultOptions: {
        queries: {
            retry: 1,
            refetchOnWindowFocus: false,
        },
    },
})

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
            <QueryClientProvider client={queryClient}>
                <StatusBar style="dark" />
                <Stack>
                    <Stack.Screen
                        name="(tabs)"
                        options={{ headerShown: false }}
                    />
                    <Stack.Screen
                        name="LocationSearch"
                        options={{ headerShown: false }}
                    />
                    <Stack.Screen
                        name="BusResults"
                        options={{ headerShown: false }}
                    />
                </Stack>
            </QueryClientProvider>
        </>
    )
}
