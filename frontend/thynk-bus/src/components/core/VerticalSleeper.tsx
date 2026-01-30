import { SLEEPER_HEIGHT, SLEEPER_WIDTH } from '@/src/constants'
import { TripStageSeat } from '@/src/models'
import Ionicons from '@expo/vector-icons/Ionicons'
import { View, Text, TouchableOpacity, StyleSheet } from 'react-native'

type Props = {
    seat: TripStageSeat
    onPress: (seat: TripStageSeat) => void
    selected: boolean
}

export const VerticalSleeper = ({ seat, selected, onPress }: Props) => {
    if (seat.aisle) {
        return <View style={{ height: 24 }} />
    }

    if (seat.sold) {
        return (
            <View style={styles.soldSleeper}>
                <Ionicons name="close" size={20} color="#B0B0B0" />
                <Text style={styles.soldText}>Sold</Text>
            </View>
        )
    }

    return (
        <TouchableOpacity
            onPress={() => onPress(seat)}
            style={[styles.sleeper, selected && styles.selected]}
        >
            {!selected && <Text style={styles.price}>₹{seat.price}</Text>}
            <View style={styles.pillow} />
        </TouchableOpacity>
    )
}

const styles = StyleSheet.create({
    selected: { backgroundColor: '#2E7D32' },
    sleeper: {
        width: SLEEPER_WIDTH,
        height: SLEEPER_HEIGHT,
        borderRadius: 5,
        borderWidth: 2,
        borderColor: '#2E7D32',
        justifyContent: 'flex-end',
        alignItems: 'center',
        marginBottom: 20,
        backgroundColor: '#FFF',
    },
    pillow: {
        width: 28,
        height: 6,
        borderRadius: 4,
        backgroundColor: '#E0F2E9',
        marginBottom: 8,
    },
    price: {
        fontSize: 10,
        color: '#333',
        fontFamily: 'Montserrat_500Medium',
    },
    soldSleeper: {
        width: 48,
        height: 96,
        borderRadius: 8,
        borderWidth: 2,
        borderColor: '#D0D7E2',
        justifyContent: 'center',
        alignItems: 'center',
        marginBottom: 16,
        backgroundColor: '#F5F7FB',
    },
    soldText: {
        fontSize: 10,
        color: '#9AA3AF',
        marginTop: 4,
        fontFamily: 'Montserrat_500Medium',
    },
})
