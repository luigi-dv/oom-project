package src.infrastructure.utilities.filereaders;

import src.domain.entities.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CredentialsReader {

    /**
     * Path to the credentials file storing user authentication information.
     */
    protected static final String filePath = "src/infrastructure/persistance/data/credentials.txt";

    /**
     * Checks if the credentials file exists.
     * @return True if the credentials file exists, false otherwise.
     */
    public static boolean doesFileExist() {
        return Files.exists(Path.of(filePath));
    }

    /**
     * Reads a user from the credentials file by their username.
     * @param username The username of the user to be retrieved.
     * @return The user with the specified username or null if not found.
     */
    public static User readUserByUsername(String username) {

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(username + ":")) {
                    // User entry found, parse the line and construct a User object
                    return parseUserFromLine(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle IOException appropriately
        } catch (Exception e) {
            e.printStackTrace(); // Handle other exceptions appropriately
        }

        // If the username is not found, return null
        return null;
    }

    /**
     * Parses a User object from a line in the credentials file.
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
}
