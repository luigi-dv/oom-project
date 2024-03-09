package src.application.controllers;

import src.application.services.ProfileService;
import src.application.providers.SessionProvider;
import src.domain.entities.User;

/**
 * The UIController class is responsible for handling the UI logic.
 */
public class UIController {

    /**
     * The profile service to be used by the UIController.
     */
    private final ProfileService profileService;

    /**
     * The session provider to be used by the UIController.
     */
    private final SessionProvider sessionProvider;

    /**
     * Constructor for the UIController class.
     */
    public UIController() {
        this.sessionProvider = SessionProvider.getInstance();
        this.profileService = new ProfileService();
    }

    /**
     * Authenticates a user with the provided username and password.
     *
     * @return True if the user was successfully authenticated, null otherwise.
     */
    public User getAuthenticatedUser() {
        return sessionProvider.getAuthenticatedUser();
    }

    /**
     * Initialize the profile for a user
     *
     * @param user the user to initialize the profile for
     */
    public void initializeProfile(User user){
        profileService.createProfileFromUser(user);
    }
}

