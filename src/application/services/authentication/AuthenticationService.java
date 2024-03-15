package src.application.services.authentication;

import src.application.providers.SessionProvider;
import src.application.services.EncryptService;
import src.application.services.UserService;
import src.domain.entities.User;
import src.infrastructure.utilities.Crypter;


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
        userService = new UserService();
    }

    /**
     * Authenticates a user with the provided username and password.
     *
     * @param username The username
     * @param password The password
     */
    public User authenticateUser(String username, String password) {

        try {
            User user = userService.getUserByUsername(username);

            if (user != null && validatePassword(user, password)) {
                sessionProvider.setAuthenticatedUser(user);
                return user;
            } else {
                System.out.println("Authentication failed for user: " + username);
                // Log authentication failure or throw an exception
                // depending on your application's error handling strategy.
                // Example: LOGGER.error("Authentication failed for user: {}", username);
            }
        } catch (Exception e) {
            // Handle exceptions appropriately (e.g., log or throw)
        }

        return null;
    }

    /**
     * Validates the provided password for the given user.
     * @param user The user
     * @param password The password to validate
     * @return True if the password is valid, false otherwise
     * @implNote This method should be overridden in subclasses to implement secure password validation logic.
     */
    private boolean validatePassword(User user, String password) throws Exception {
        // Implement secure password validation logic here
        // e.g., using a secure hashing algorithm
        return user.getPassword().equals(Crypter.StringToEncryptedString(password));
    }

    /**
     * Returns the currently authenticated user.
     * @return The currently authenticated user or null if no user is authenticated.
     */
    public User getAuthenticatedUser() {
        return SessionProvider.getInstance().getAuthenticatedUser();
    }

    /**
     * De-authenticates the currently authenticated user.
     */
    public void deAuthenticateUser() {
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


    /**
     * Registers a new user with the provided username, password, and bio.
     *
     * @param user The user to register
     */
    public void registerUser(User user) {
        if(userService.createUser(user) != null){
            authenticateUser(user.getUsername(), user.getPassword());
        }
    }
}

