package src.application.controllers;

import src.domain.entities.User;
import src.application.services.ProfileService;

/**
 * The UIController class is responsible for handling the UI logic.
 */
public class UIController extends BaseController {

    /**
     * The profile service to be used by the UIController.
     */
    private final ProfileService profileService;

    /**
     * Constructor for the UIController class.
     */
    public UIController() {
        this.profileService = new ProfileService();
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

