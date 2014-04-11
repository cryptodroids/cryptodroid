package com.cryptodroids.encryption;

/**
 * Implementation of the EncryptionHelper that just passed through the bytes.
 * <p/>
 * Useful for testing, or if you don't really want encryption.
 * <p/>
 * Created by chris on 11/04/2014.
 * For cryptodroid.
 */
public class NoEncryptionHelper implements EncryptionHelper {

    @Override
    public byte[] encrypt(byte[] bytes) throws EncryptionException {
        return bytes;
    }

    @Override
    public byte[] decrypt(byte[] bytes) throws EncryptionException {
        return bytes;
    }

}
