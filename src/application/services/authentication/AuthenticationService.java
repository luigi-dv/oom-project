package src.application.services.authentication;

import src.application.providers.SessionProvider;
import src.application.services.EncryptService;
import src.application.services.UserService;
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
        userService = new UserService();
    }

    /**
     * Path to the directory where user profile photos are stored.
     */
    protected final String profilePhotoStoragePath = "resources/storage/images/";

    /**
     * Authenticates a user with the provided username and password.
     *
     * @param username The username
     * @param password The password
     */
    public User authenticateUser(String username, String password) {
        if(userService.getUserByUsername(username) != null){
            User user = userService.getUserByUsername(username);
            if(!user.getPassword().equals(password)){
                return null;
            }
            sessionProvider.setAuthenticatedUser(user);
            return user;
        }
        return null;
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
        if(saveCredentials(user) == null){
            return;
        }
        authenticateUser(user.getUsername(), user.getPassword());
    }


    /**
     * Saves the provided credentials to the database.

     * @return The created user entity.
     */
    private User saveCredentials(User user) {
        return userService.createUser(user);
    }


}

