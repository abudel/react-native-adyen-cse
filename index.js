import { NativeModules } from 'react-native';

const { RNAdyenCSE } = NativeModules

export const encrypt = async (cardDetail, publicKey) => {
    const { holderName, number, cvc, expiryMonth, expiryYear } = cardDetail
    token = await RNAdyenCSE.encrypt(holderName, number, cvc, expiryMonth, expiryYear, publicKey)
    return token
}