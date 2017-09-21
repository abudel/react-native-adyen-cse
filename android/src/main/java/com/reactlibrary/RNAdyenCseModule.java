
package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import adyen.com.adyencse.encrypter.exception.EncrypterException;

import adyen.com.adyencse.pojo.Card;

import java.util.Date;

public class RNAdyenCSEModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNAdyenCSEModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNAdyenCSE";
  }

  @ReactMethod
  public void encrypt(String holderName, String number, String cvc, String expiryMonth, String expiryYear,
      String publicKey, Promise promise) {

    Card card = new Card();
    card.setCardHolderName(holderName);
    card.setCvc(cvc);
    card.setExpiryMonth(expiryMonth);
    card.setExpiryYear(expiryYear);
    card.setGenerationTime(new Date());
    card.setNumber(number);

    try {
      //	Encrypt card data
      String encryptedCard = card.serialize(publicKey);

      promise.resolve(encryptedCard);
    } catch (EncrypterException e) {
      promise.reject("ADYEN_CSE_ERROR", e);
    }
  }
}