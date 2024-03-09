package src.application.controllers;

import src.domain.entities.User;
import src.application.services.authentication.SignInService;

public class SignInController extends BaseController {

    /**
     * The service responsible for signing in users.
     */
    private final SignInService signInService;

    /**
     * Constructs an instance of SignInController, initializing the SignInService.
     */
    public SignInController() {
        this.signInService = new SignInService();
    }

    /**
     * Signs in a user with the provided username and password.
     *
     * @param username The username to check
     * @param password The password to check
     */
    public boolean signIn(String username, String password) {
        return signInService.verifyCredentials(username, password) != null;
    }
}
