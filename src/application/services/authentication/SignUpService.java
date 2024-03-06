package src.application.services.authentication;


import java.io.*;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import src.infrastructure.utilities.Crypter;
import src.application.services.encription.EncryptService;

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
     * Method to check if the username already exists
     * @param username The username
     * @return True if the username exists, false otherwise
     */
    public boolean doesUsernameExist(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(credentialsFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(Crypter.StringToEncryptedString(username) + ":")) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/credentials.txt", true))) {
            writer.write(EncryptService.generateEncryptInformation(username, password, bio));
            writer.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
