package src.application.controllers;

import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.filechooser.FileNameExtensionFilter;
import src.application.services.authentication.SignUpService;
import src.application.views.SignInUI;

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
    private void handleProfilePictureUpload(String username) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(null); // Use null as the parent component

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            signUpService.saveProfilePicture(selectedFile, username);
        }
    }

    public boolean register(String username, String password, String bio) {
        if (signUpService.doesUsernameExist(username)) {
            return false;
        } else {
            signUpService.saveCredentials(username, password, bio);
            handleProfilePictureUpload(username);
            return true;
        }
    }
}
