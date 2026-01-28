import { View, Text, ViewStyle, StyleSheet } from 'react-native'
import React from 'react'

type CardProps = {
    children: React.ReactNode
    style?: ViewStyle
}

const Card = ({ children, style }: CardProps) => {
    return <View style={{...styles.card, ...style}}>{children}</View>
}

const styles = StyleSheet.create({
    card: {
        width: '100%',
        backgroundColor: '#fff',
        borderRadius: 5,
        padding: 16,
        shadowColor: '#333',
        shadowOpacity: 0.25,
        shadowRadius: 3,
        elevation: 2,
    },
})

export default Card
