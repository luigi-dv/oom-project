package src.application.services.authentication;

import src.domain.entities.User;

/**
 * Service class responsible for signing in users.
 */
public class SignInService extends AuthenticationService {

    /**
     * Constructs an instance of SignInService, initializing the AuthenticationService.
     */
    public SignInService() {

    }

    /**
     * Verifies the provided username and password.
     * @param username The username
     * @param password The password
     * @return The authenticated user if the credentials are valid.
     */
    public User verifyCredentials(String username, String password) {
        return this.authenticateUser(username, password);
    }
}
