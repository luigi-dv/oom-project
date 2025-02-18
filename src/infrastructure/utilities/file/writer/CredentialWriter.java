package src.infrastructure.utilities.file.writer;

import java.io.*;
import src.domain.entities.User;
import src.infrastructure.utilities.Crypter;
import src.infrastructure.utilities.file.IFile;

/**
 * Utility class responsible for managing user credentials in a file.
 * Implements the IFileWriter interface for generic file writing functionality.
 */
public class CredentialWriter implements IFile {

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
            writer.write(user.getUsername() + ":" +
                    Crypter.StringToEncryptedString(user.getPassword()) + ":" +
                    user.getBio());
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
        File inputFile = new File(FILE_PATH);
        File tempFile = new File("temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(user.getUsername())) {
                    line = user.toString();
                }
                writer.write(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        // Rename the temporary file to the original file
        if (!inputFile.delete()) {
            System.err.println("Could not delete original file");
            return null;
        }
        if (!tempFile.renameTo(inputFile)) {
            System.err.println("Could not rename temporary file");
        }

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
