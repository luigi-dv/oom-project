package src.infrastructure.utilities.file.reader;

import java.nio.file.Path;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.io.BufferedReader;
import src.domain.entities.User;
import src.infrastructure.utilities.file.IFile;

/**
 * Utility class responsible for reading user credentials from a file.
 * This class provides methods to check the existence of the credentials file,
 * and retrieve user information by their username.
 */
public class CredentialsReader implements IFile {

    /**
     * Path to the file.
     */
    private static final String FILE_PATH = FILE_PATH_ROOT + "credentials.txt";

    /**
     * Checks if the credentials file exists.
     *
     * @return True if the credentials file exists, false otherwise.
     */
    public static boolean doesFileExist() {
        return Files.exists(Path.of(FILE_PATH));
    }

    /**
     * Reads a user from the credentials file by their username.
     *
     * @param username The username of the user to be retrieved.
     * @return The user with the specified username or null if not found.
     */
    public static User readUserByUsername(String username) {

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(username + ":")) {
                    // User entry found, parse the line and construct a User object
                    return parseUserFromLine(line);
                }
            }
        } catch (IOException e) {
            handleIOException(e); // Handle IOException appropriately
        } catch (Exception e) {
            handleOtherException(e); // Handle other exceptions appropriately
        }

        // If the username is not found, return null
        return null;
    }

    /**
     * Parses a User object from a line in the credentials file.
     *
     * @param line The line to parse.
     * @return The User object parsed from the line.
     */
    private static User parseUserFromLine(String line) {
        // Split the line using ":" as a separator
        String[] parts = line.split(":");

        // Assuming the format is "encryptedUsername:bio:encryptedPassword"
        String username = parts[0];
        String bio = parts[1];
        String password = parts[2];

        // Create and return a new User object
        return new User(username, bio, password);
    }

    /**
     * Handles IOException during file reading by printing the stack trace.
     *
     * @param e The IOException.
     */
    private static void handleIOException(IOException e) {
        e.printStackTrace();
        // You may choose to log the exception or handle it differently based on your application's requirements.
    }

    /**
     * Handles other exceptions during file parsing by printing the stack trace.
     *
     * @param e The exception.
     */
    private static void handleOtherException(Exception e) {
        e.printStackTrace();
        // You may choose to log the exception or handle it differently based on your application's requirements.
    }
}
