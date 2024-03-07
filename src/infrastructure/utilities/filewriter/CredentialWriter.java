package src.infrastructure.utilities.filewriter;

import src.domain.entities.User;
import src.infrastructure.utilities.Crypter;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class CredentialWriter {

    /**
     * Path to the credentials file storing user authentication information.
     */
    protected static final String credentialsFilePath = "src/infrastructure/persistance/data/credentials.txt";

    /**
     * Writes the user credentials to the credentials file.
     * @param user The User entity to save
     */
    public static User writeCredentials(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(credentialsFilePath, true))) {
            writer.write(user.getUsername() + ":" + user.getPassword() + ":" + user.getBio());
            writer.newLine();
            return user;
        } catch (Exception e) {
            // TODO: Catch exception
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Updates the user credentials in the credentials file.
     * @param user The User entity to update
     * @return The updated User entity
     */
    public static User updateCredentials(User user) {
        // TODO: Implement update credentials (search line with the username, update the line, save the file)
        return null;
    }

    /**
     * Deletes the user credentials from the credentials file.
     * @param user The User entity to delete
     * @return The deleted User entity
     */
    public static User deleteCredentials(User user) {
        // TODO: Implement delete credentials (search line with the username, delete the line, save the file)
        return null;
    }
}
