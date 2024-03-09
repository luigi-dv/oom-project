package src.infrastructure.utilities.file.reader;

import java.util.List;
import java.util.UUID;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import src.domain.entities.User;
import src.domain.entities.Picture;
import src.infrastructure.utilities.file.IFile;

/**
 * Utility class for reading Picture entities from a file.
 * Implements the IFile interface for generic file reading functionality.
 */
public class PictureReader implements IFile {

    /**
     * Path to the file storing Picture entities.
     */
    private static final String FILE_PATH = FILE_PATH_ROOT + "pictures.txt";

    /**
     * Reads all Picture entities from the file.
     *
     * @return A list of all Picture entities in the file.
     */
    public static List<Picture> findAll() {
        List<Picture> pictures = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Picture picture = parsePictureFromLine(line);
                pictures.add(picture);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle IOException appropriately
        } catch (Exception e) {
            e.printStackTrace(); // Handle other exceptions appropriately
        }
        return pictures;
    }


    /**
     * Finds and retrieves a Picture by its unique identifier.
     *
     * @param id The unique identifier of the picture to be retrieved.
     * @return The Picture with the specified ID or null if not found.
     */
    public static Picture findById(UUID id) {
        String stringId = id.toString();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(stringId)) {
                    // Picture entry found, parse the line and construct a Picture object
                    return parsePictureFromLine(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle IOException appropriately
        } catch (Exception e) {
            e.printStackTrace(); // Handle other exceptions appropriately
        }

        // If the picture is not found, return null
        return null;
    }

    /**
     * Parses a Picture object from a line in the credentials file.
     *
     * @param line The line to parse.
     * @return The Picture object parsed from the line.
     */
    private static Picture parsePictureFromLine(String line) {
        String[] parts = line.split(":");
        String stringId = parts[0];
        UUID id = UUID.fromString(stringId);
        String username = parts[1];
        User user = new User(username);
        String imagePath = parts[2];
        String caption = parts[3];

        return new Picture(id, user, imagePath, caption);
    }

    /**
     * Finds and retrieves a list of Pictures associated with a specific User.
     *
     * @param user The User entity to retrieve pictures for.
     * @return The list of Pictures associated with the specified User.
     */
    public static List<Picture> findPicturesFromUser(User user) {
        String username = user.getUsername();
        List<Picture> pictures = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Picture picture = parsePictureFromUser(line, username);
                if (picture != null) {
                    pictures.add(picture);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle IOException appropriately
        } catch (Exception e) {
            e.printStackTrace(); // Handle other exceptions appropriately
        }

        return pictures;
    }

    /**
     * Parses a Picture object from a line in the credentials file, filtering by the specified username.
     *
     * @param line     The line to parse.
     * @param username The username to filter by.
     * @return The Picture object parsed from the line if associated with the specified username, otherwise null.
     */
    private static Picture parsePictureFromUser(String line, String username) {
        String[] parts = line.split(":");
        String getUsername = parts[1];
        if (getUsername.equals(username)) {
            return null;
        }
        String stringId = parts[0];
        UUID id = UUID.fromString(stringId);
        User user = new User(username);
        String imagePath = parts[2];
        String caption = parts[3];

        return new Picture(id, user, imagePath, caption);
    }
}
