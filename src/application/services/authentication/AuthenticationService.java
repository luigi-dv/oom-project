package src.application.services.authentication;

import java.nio.file.Path;
import java.nio.file.Files;
import src.application.services.encription.EncryptService;


/**
 * Abstract base class providing common functionality for user authentication services.
 * Subclasses should extend this class to implement specific authentication logic.
 */
public abstract class AuthenticationService {

    /**
     * Service responsible for encryption and decryption operations.
     */
    protected final EncryptService encryptService;

    /**
     * Constructs an instance of AuthenticationService initializing the EncryptService.
     */
    public AuthenticationService() {
        encryptService = new EncryptService();
    }

    /**
     * Path to the credentials file storing user authentication information.
     */
    protected final String credentialsFilePath = "data/credentials.txt";

    /**
     * Path to the directory where user profile photos are stored.
     */
    protected final String profilePhotoStoragePath = "img/storage/profile/";

    /**
     * Checks if the credentials file exists.
     *
     * @return True if the credentials file exists, false otherwise.
     */
    protected boolean isCredentialsFileExists() {
        return Files.exists(Path.of(credentialsFilePath));
    }

    /**
     * Checks if the profile photo storage directory exists.
     *
     * @return True if the profile photo storage directory exists, false otherwise.
     */
    protected boolean isProfilePhotoStorageDirectoryExists() {
        return Files.exists(Path.of(profilePhotoStoragePath));
    }
}

