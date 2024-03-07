package src.application.controllers;

import src.application.services.ProfileService;
import src.application.services.authentication.AuthenticationService;
import src.application.providers.SessionProvider;
import src.domain.entities.User;

public class UIController {

    private final AuthenticationService authenticationService;
    private final ProfileService profileService;
    private final SessionProvider sessionProvider;

    public UIController() {
        this.authenticationService = new AuthenticationService();
        this.sessionProvider = SessionProvider.getInstance();
        this.profileService = new ProfileService();
    }

    public User getAuthenticatedUser() {
        return sessionProvider.getAuthenticatedUser();
    }

    public void authenticateUser(String username, String password) {
        User authenticatedUser = authenticationService.authenticateUser(username, password);
        // TODO: Handle UI updates or navigation after authentication
    }

    public void deAuthenticateUser() {
        authenticationService.deAuthenticateUser();
        // TODO: Handle UI updates or navigation after de-authentication
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

