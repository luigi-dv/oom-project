package src.presentation.controllers;

import src.application.providers.SessionProvider;
import src.domain.entities.User;

public class BaseController {

    /**
     * The session provider to be used by the UIController.
     */
    protected final SessionProvider sessionProvider;

    /**
     * Constructor for the BaseController class.
     */
    public BaseController() {
        // Initialize the session provider using the singleton pattern.
        this.sessionProvider = SessionProvider.getInstance();
    }

    /**
     * Gets the authenticated user from the session provider.
     *
     * @return True if the user was successfully authenticated, null otherwise.
     */
    public User getAuthenticatedUser() {
        // Retrieve the authenticated user from the session provider.
        return sessionProvider.getAuthenticatedUser();
    }

    public boolean isAuthenticatedUser(User user) {
        return sessionProvider.getAuthenticatedUser().getUsername().equals(user.getUsername());
    }
}
