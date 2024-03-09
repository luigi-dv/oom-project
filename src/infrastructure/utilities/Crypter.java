package src.infrastructure.utilities;/*
 * Copyright: Joa Eliëns
 * Made by: Joa Eliëns
 * Version: 2024.02.22
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * The src.infrastructure.utilities.Crypter file is used to encrypt and decrypt certain sensitive variables.
 * 
 * @since 03/12/2023
 * @author Joa V. Eliëns
 */
public class Crypter {

    /**
     * The file path of the .key file.
     */
    public final static String filePath = "src/infrastructure/persistence/data/ki.key";

    /**
     * A Dummy Key to not get errors.
     */
    public final static SecretKey DUMMY_KEY = null;

    /**
     * The {@link SecretKey}
     */
    public static SecretKey key = DUMMY_KEY;

    /**
     * The constructor for the Cypter class
     */
    public Crypter() {
    }

    /**
     * Set the {@link Crypter#key key}
     * @param key
     */
    public void setKey(SecretKey key){
        this.key = key;
    }

    /**
     * Get the {@link Crypter#key key}
     * @return key
     */
    public SecretKey getKey(){
        return key;
    }

    /**
     * Generate a secret key to {@link Crypter#encrypt(String, SecretKey) encrypt} and {@link Crypter#decrypt(byte[], SecretKey) decrypt} the String and bytes
     * @return the {@link SecretKey}
     * @throws Exception
     */
    private SecretKey generateSecretKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");

        return keyGenerator.generateKey();
    }

    /**
     * Encrypt the String with the given {@link SecretKey} to bytes
     * @param text
     * @param key
     * @return the encrypted bytes
     * @throws Exception
     */
    public static byte[] encrypt(String text, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        return cipher.doFinal(text.getBytes());
    }

    /**
     * Get the encrypted String of a non-encrypted String
     * @param text
     * @return the encrypted String
     * @throws Exception
     */
    public static String StringToEncryptedString(String text) throws Exception{
        return Base64.getEncoder().encodeToString(encrypt(text, key));
    }

    /**
     * Get the decrypted String of the encrypted String
     * @param encrypted
     * @return the decrypted String
     * @throws Exception
     */
    public static String encryptedStringToString(String encrypted) throws Exception {
        return decrypt(convertBase64StringToBytes(encrypted), key);
    }

    /**
     * Decrypt the bytes with the given {@link SecretKey} to a String
     * @param encryptedText
     * @param key
     * @return the decrypted String
     * @throws Exception
     */
    public static String decrypt(byte[] encryptedText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);

        byte[] decryptedBytes = cipher.doFinal(encryptedText);

        return new String(decryptedBytes);
    }

    /**
     * Save the {@link SecretKey} to the given file
     * @param key
     * @throws IOException
     */
    private void saveSecretKey(SecretKey key) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(key);
        }
    }

    /**
     * Get the {@link SecretKey} from the file
     * @return the {@code SecretKey}
     * @throws Exception
     */
    public SecretKey loadSecretKey() throws Exception {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            return (SecretKey) ois.readObject();
        }
    }

    /**
     * Generate a {@link SecretKey} and save it to the file
     * @return the generated {@code SecretKey}
     * @throws Exception
     */
    public SecretKey generateAndSaveSecretKey() throws Exception {
        SecretKey secretKey = generateSecretKey();

        saveSecretKey(secretKey);

        return secretKey;
    }

    /**
     * Convert the String to a byte array
     * 
     * @param base64String
     * @return the byte array
     * @see Base64
     */
    private static byte[] convertBase64StringToBytes(String base64String) {
        return Base64.getDecoder().decode(base64String);
    }

}
