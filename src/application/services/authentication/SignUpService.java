package src.application.services.authentication;

import javax.imageio.ImageIO;
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
    // private void onRegisterClicked(ActionEvent event) {
    //     // TODO: Implement callback for register button and handle user registration
    // }

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
}
