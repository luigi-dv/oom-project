package src.application.services.encription;

import java.nio.file.Path;
import java.nio.file.Files;
import src.infrastructure.utilities.Crypter;

/**
 * Service class responsible for encryption and decryption operations.
 */
public class EncryptService {

    /**
     * Instance of the Crypter class for cryptographic operations.
     */
    private final Crypter crypter;

    /**
     * Constructor to initialize the encryption
     */
    public EncryptService() {
        crypter = new Crypter();
        initEncryption();
    }

    /**
     * Generates encrypted information for a given username, password, and biography.
     *
     * @param username The username to be encrypted.
     * @param password The password to be encrypted.
     * @param bio      The biography to be encrypted.
     * @return The encrypted information in the format: username:password:bio.
     * @throws Exception If an encryption exception occurs.
     */
    public static String generateEncryptInformation(String username, String password, String bio) throws Exception {
        return Crypter.StringToEncryptedString(username) + ":" + Crypter.StringToEncryptedString(password) + ":" + Crypter.StringToEncryptedString(bio);
    }

    /**
     * Decrypts the provided text.
     *
     * @param text The text to be decrypted.
     * @return The decrypted text.
     * @implNote This method is a placeholder and currently returns the input text as-is.
     */
    public static String decrypt(String text) {
        return text;
    }

    /**
     * Method to initialize the encryption
     */
    private void initEncryption(){
        try {
            if (Files.exists(Path.of(Crypter.filePath))) {
                Crypter.key = crypter.loadSecretKey();
            } else {
                Crypter.key = crypter.generateAndSaveSecretKey();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
