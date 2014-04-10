package com.cryptodroids.encryption;

public interface EncryptionHelper {
    /**
     * Encrypts the given bytes.
     *
     * @param bytes The bytes to encrypt.
     * @return The encrypted bytes.
     * @throws EncryptionException When the encryption fails.
     */
    byte[] encrypt(byte[] bytes) throws EncryptionException;

    /**
     * Decrypts the given bytes.
     *
     * @param bytes The bytes to decrypt.
     * @return The decrypted bytes.
     * @throws EncryptionException When the encryption fails.
     */
    byte[] decrypt(byte[] bytes) throws EncryptionException;
}