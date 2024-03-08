package src.application.controllers;


import java.io.File;
import javax.swing.*;
import src.domain.entities.User;
import javax.swing.filechooser.FileNameExtensionFilter;
import src.application.services.authentication.SignUpService;


/**
 * Controller class responsible for handling user sign-up operations.
 */
public class SignUpController {

    /**
     * Service responsible for handling sign-up logic.
     */
    private final SignUpService signUpService;

    /**
     * Constructs an instance of SignUpController, initializing the SignUpService.
     */
    public SignUpController() {
        signUpService = new SignUpService();
    }

    /**
     * Handles the profile picture upload when the register button is clicked.
     *
     * @param username The username of the user for whom the profile picture is being uploaded.
     */
    public void handleProfilePictureUpload(String username) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(null); // Use null as the parent component

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            signUpService.saveProfilePicture(selectedFile, username);
        }
    }

    /**
     * Registers a new user with the provided username, password, and bio.
     *
     * @param username The username
     * @param password The password
     * @param bio The biography
     * @return True if the user was successfully registered, false otherwise.
     */
    public boolean register(String username, String password, String bio) {
        if (signUpService.doesUsernameExist(username)) {
            return false;
        } else {
            User newUser = new User(username, password, bio);
            signUpService.registerUser(newUser);
            handleProfilePictureUpload(username);
            return true;
        }
    }

    /**
     * Retrieves the authenticated user with the provided username.
     *
     * @return The authenticated user or null if no user is authenticated.
     */
    public User getAuthenticatedUser() {
        return signUpService.getAuthenticatedUser();
    }
}
