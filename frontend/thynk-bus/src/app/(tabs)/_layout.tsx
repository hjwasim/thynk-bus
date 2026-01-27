import { Tabs } from 'expo-router'
import AntDesign from '@expo/vector-icons/AntDesign'

export default function RootLayout() {
    return (
        <Tabs
            screenOptions={{
                tabBarActiveTintColor: 'blue',
                headerShown: false,
                tabBarItemStyle: {
                    marginTop: 6,
                },
                tabBarLabelStyle: {
                    fontFamily: 'Montserrat_500Medium',
                },
            }}
        >
            <Tabs.Screen
                name="Home"
                options={{
                    title: 'Home',
                    tabBarIcon: ({ color }) => (
                        <AntDesign name="home" size={24} color={color} />
                    ),
                }}
            />
            <Tabs.Screen
                name="Bookings"
                options={{
                    title: 'Bookings',
                    tabBarIcon: ({ color }) => (
                        <AntDesign name="wallet" size={24} color={color} />
                    ),
                }}
            />
            <Tabs.Screen
                name="Help"
                options={{
                    title: 'Help',
                    tabBarIcon: ({ color }) => (
                        <AntDesign name="question" size={24} color={color} />
                    ),
                }}
            />
            <Tabs.Screen
                name="Profile"
                options={{
                    title: 'Profile',
                    tabBarIcon: ({ color }) => (
                        <AntDesign name="profile" size={24} color={color} />
                    ),
                }}
            />
        </Tabs>
    )
}
