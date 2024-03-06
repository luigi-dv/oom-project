package src.application.services.authentication;

import src.application.providers.SessionProvider;
import src.application.services.encription.EncryptService;
import src.application.services.user.UserService;
import src.domain.entities.User;


/**
 * Abstract base class providing common functionality for user authentication services.
 * Subclasses should extend this class to implement specific authentication logic.
 */
public class AuthenticationService {

    /**
     * Service responsible for encryption and decryption operations.
     */
    protected final EncryptService encryptService;

    /**
     * Service responsible for User operations.
     */
    protected final UserService userService;

    protected final SessionProvider sessionProvider;


    /**
     * Constructs an instance of AuthenticationService initializing the EncryptService.
     */
    public AuthenticationService() {
        encryptService = new EncryptService();
        sessionProvider = SessionProvider.getInstance();
        userService = new UserService(SessionProvider.getInstance());
    }

    /**
     * Path to the directory where user profile photos are stored.
     */
    protected final String profilePhotoStoragePath = "img/storage/profile/";

    /**
     * Authenticates a user with the provided username and password.
     *
     * @param username The username
     * @param password The password
     */
    protected void authenticateUser(String username, String password) {
        // TODO: Implement user authentication logic reading documents from the credentials file
        // For now, we'll just set a dummy authenticated user
        User authenticatedUser = new User(username, "Bio", password);

        // Set the authenticated user in the session provider
        SessionProvider.getInstance().setAuthenticatedUser(authenticatedUser);
    }

    /**
     * De-authenticates the currently authenticated user.
     */
    protected void deAuthenticateUser() {
        // Clear the authenticated user in the session provider
        SessionProvider.getInstance().clearSession();
    }

    /**
     * Method to check if the username already exists
     * @param username The username
     * @return True if the username exists, false otherwise
     */
    public boolean doesUsernameExist(String username) {
        return userService.getUserByUsername(username) != null;
    }
}

