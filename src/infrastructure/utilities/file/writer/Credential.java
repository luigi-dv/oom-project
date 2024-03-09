package src.infrastructure.utilities.file.writer;

import java.io.FileWriter;
import java.io.BufferedWriter;
import src.domain.entities.User;
import src.infrastructure.utilities.file.IFile;

/**
 * Utility class responsible for managing user credentials in a file.
 * Implements the IFileWriter interface for generic file writing functionality.
 */
public class Credential implements IFile {

    /**
     * Path to the file.
     */
    protected static final String FILE_PATH = FILE_PATH_ROOT + "credentials.txt";

    /**
     * Writes the user credentials to the credentials file.
     *
     * @param user The User entity whose credentials are to be saved.
     * @return The User entity with the saved credentials.
     */
    public static User writeToFile(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            // Concatenate username, password, and bio with ":" as a separator
            writer.write(user.getUsername() + ":" + user.getPassword() + ":" + user.getBio());
            writer.newLine();
            return user;
        } catch (Exception e) {
            // TODO: Handle exceptions appropriately (e.g., logging, throwing custom exception)
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Updates the user credentials in the credentials file.
     *
     * @param user The User entity whose credentials are to be updated.
     * @return The updated User entity.
     */
    public static User updateCredentials(User user) {
        // TODO: Implement update credentials (search line with the username, update the line, save the file)
        return null;
    }

    /**
     * Deletes the user credentials from the credentials file.
     *
     * @param user The User entity whose credentials are to be deleted.
     * @return The deleted User entity.
     */
    public static User deleteCredentials(User user) {
        // TODO: Implement delete credentials (search line with the username, delete the line, save the file)
        return null;
    }
}
