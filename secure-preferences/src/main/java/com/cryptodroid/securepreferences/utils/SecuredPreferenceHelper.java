package com.cryptodroid.securepreferences.utils;

import android.util.Log;

import com.cryptodroids.encryption.EncryptionException;
import com.cryptodroids.encryption.EncryptionHelper;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;

import okio.Buffer;
import okio.ByteString;

/**
 * Created by chris on 10/04/2014.
 * For cryptodroid.
 */
public class SecuredPreferenceHelper {

    private final EncryptionHelper helper;

    SecuredPreferenceHelper(EncryptionHelper helper) {
        this.helper = helper;
    }

    /**
     * Using Squares Okio methods to convert data into base64 string
     */
    protected String encode(byte[] input) {
        return ByteString.of(input).base64();
    }

    /**
     * Using Squares Okio method to convert Base64 string into a ByteArray
     */
    protected byte[] decode(String input) {
        return ByteString.decodeBase64(input).toByteArray();
    }

    public <T> String encrypt(T object) {
        String result = null;
        if (object != null) {
            try {
                result = encryptToString(object);
            } catch (EncryptionException e) {
                Log.e("SecurePreference", "Failed to encrypt value", e);
            }
        }
        return result;
    }

    private <T> String encryptToString(T object) throws EncryptionException {
        String result;
        try {
            final Buffer buffer = new Buffer();
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(buffer.outputStream());
            objectOutputStream.writeObject(object);
            final byte[] bytes = buffer.readByteString(buffer.size()).toByteArray();
            final byte[] encrypted = helper.encrypt(bytes);
            result = encode(encrypted);
        } catch (UnsupportedEncodingException e) {
            throw new EncryptionException(e);
        } catch (IOException e) {
            throw new EncryptionException(e);
        }
        return result;
    }

    public <T> T decrypt(String encyptedValue, T defValue) throws EncryptionException {
        T value = defValue;
        if (encyptedValue != null) {
            try {
                final byte[] decodedBytes = decode(encyptedValue);
                final byte[] decoded = helper.decrypt(decodedBytes);
                final Buffer buffer = new Buffer().write(decoded);
                final ObjectInputStream objectInputStream = new ObjectInputStream(buffer.inputStream());
                value = (T) objectInputStream.readObject();
            } catch (IOException e) {
                throw new EncryptionException(e);
            } catch (ClassNotFoundException e) {
                throw new EncryptionException(e);
            }
        }
        return value;
    }

}
