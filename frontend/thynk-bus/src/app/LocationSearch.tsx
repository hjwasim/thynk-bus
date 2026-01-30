import {
    View,
    Text,
    Pressable,
    TextInput,
    StyleSheet,
    FlatList,
    TouchableOpacity,
    ListRenderItemInfo,
} from 'react-native'
import React, { useState } from 'react'
import { SafeAreaView } from 'react-native-safe-area-context'
import Ionicons from '@expo/vector-icons/Ionicons'
import { router, useLocalSearchParams } from 'expo-router'
import Fontisto from '@expo/vector-icons/Fontisto'
import { StationResponse } from '../models'
import useSearchStore from '../stores/useSearchStore'
import { useSuspenseQuery } from '@tanstack/react-query'
import { getAllStations } from '../api/stationApi'

const LocationSearch = () => {
    const { from } = useLocalSearchParams()
    const [query, setQuery] = useState<string>('')

    const { data } = useSuspenseQuery({
        queryKey: ['stations'],
        queryFn: getAllStations,
    })

    const { setFrom, setTo } = useSearchStore()

    const filteredLocations = data.filter((item) =>
        item.name.toLowerCase().startsWith(query.toLowerCase())
    )

    const renderItem = ({ item }: ListRenderItemInfo<StationResponse>) => (
        <TouchableOpacity
            onPress={() => {
                if (from === 'from') {
                    setFrom(item.id, item.name)
                } else {
                    setTo(item.id, item.name)
                }
                router.replace({
                    pathname: '/(tabs)/Home',
                    params: { from: 'childBack' },
                })
            }}
            style={styles.row}
        >
            <Ionicons name="home" size={20} color="#666" />
            <Text style={styles.rowText}>{item.name}</Text>
        </TouchableOpacity>
    )

    return (
        <SafeAreaView edges={['top']} style={{ flex: 1 }}>
            <View>
                <View style={styles.searchBox}>
                    <Pressable onPress={() => router.back()}>
                        <Ionicons
                            style={{ marginLeft: 5 }}
                            name="arrow-back"
                            size={24}
                        />
                    </Pressable>
                    <TextInput
                        placeholder="Search for a location"
                        value={query}
                        onChangeText={setQuery}
                        style={styles.input}
                    />
                    {query.length > 0 && (
                        <TouchableOpacity onPress={() => setQuery('')}>
                            <Fontisto name="close" size={20} />
                        </TouchableOpacity>
                    )}
                </View>
                <FlatList
                    data={filteredLocations}
                    keyExtractor={(item, index) => index.toString()}
                    renderItem={renderItem}
                    keyboardShouldPersistTaps="handled"
                />
            </View>
        </SafeAreaView>
    )
}

const styles = StyleSheet.create({
    searchBox: {
        flexDirection: 'row',
        alignItems: 'center',
        gap: 5,
        backgroundColor: '#fdf7ff',
        padding: 10,
        marginVertical: 5,
        shadowColor: '#333',
        shadowOpacity: 0.25,
        shadowRadius: 3,
        elevation: 2,
    },
    input: {
        padding: 10,
        fontFamily: 'Montserrat_500Medium',
        flex: 1,
        marginHorizontal: 10,
        fontSize: 16,
    },
    row: {
        flexDirection: 'row',
        alignItems: 'center',
        padding: 14,
        borderBottomWidth: 1,
        borderColor: '#b4b4b4',
    },
    rowText: {
        marginLeft: 12,
        fontSize: 15,
        fontFamily: 'Montserrat_400Regular',
    },
})

export default LocationSearch
