package src.application.services.authentication;


import src.domain.entities.User;

import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Service class responsible for user registration and authentication.
 * Extends the base AuthenticationService.
 */
public class SignUpService extends AuthenticationService {

    /**
     * Handles the register button click event.
     *
     * @param event The action event triggering the registration process.
     * @implNote This method is a placeholder for the actual registration logic.
     */
    private void onRegisterClicked(ActionEvent event) {
        // TODO: Implement callback for register button and handle user registration
    }

    /**
     * Method to save the user's profile picture to the profile photo storage path
     * @param file The file to save
     * @param username The username
     */
    public void saveProfilePicture(File file, String username) {
        try {
            BufferedImage image = ImageIO.read(file);
            File outputFile = new File(profilePhotoStoragePath + username + ".png");
            ImageIO.write(image, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to save the user's credentials to the credentials file
     * @param username The username
     * @param password The password
     * @param bio The biography
     */
    public void saveCredentials(String username, String password, String bio) {
        // TODO: This service probably return some information after create the user
        User u = new User(username, bio, password);
        userService.createUser(u);
    }
}
