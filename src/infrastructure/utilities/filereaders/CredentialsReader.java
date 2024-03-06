package src.infrastructure.utilities.filereaders;

import src.domain.entities.User;
import src.infrastructure.utilities.Crypter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CredentialsReader {

    /**
     * Path to the credentials file storing user authentication information.
     */
    protected static final String credentialsFilePath = "data/credentials.txt";

    /**
     * Checks if the credentials file exists.
     * @return True if the credentials file exists, false otherwise.
     */
    public static boolean doesFileExist() {
        return Files.exists(Path.of(credentialsFilePath));
    }

    /**
     * Reads a user from the credentials file by their username.
     * @param username The username of the user to be retrieved.
     * @return The user with the specified username or null if not found.
     */
    public static User readUserByUsername(String username) {

        try (BufferedReader reader = new BufferedReader(new FileReader(credentialsFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(Crypter.StringToEncryptedString(username) + ":")) {
                    // TODO: Read lines and construct a User Object from the data.
                    return null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // TODO: Remove mocked User
        return new User("username", "bio", "password");
    }
}
