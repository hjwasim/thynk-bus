import { View, Text, ViewStyle, StyleSheet } from 'react-native'
import React from 'react'

type CardProps = {
    children: React.ReactNode
    style?: ViewStyle
}

const Card = ({ children, style }: CardProps) => {
    return <View style={styles.card}>{children}</View>
}

const styles = StyleSheet.create({
    card: {
        width: '100%',
        backgroundColor: '#fff',
        borderRadius: 8,
        padding: 16,
        shadowColor: '#333',
        shadowOpacity: 0.25,
        shadowRadius: 4,
        elevation: 4,
    },
})

export default Card
