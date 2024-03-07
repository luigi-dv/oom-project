package src.infrastructure.utilities.filereaders;

import src.domain.entities.Picture;
import src.domain.entities.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PictureReader {

    /**
     * Path to the credentials file storing user authentication information.
     */
    protected static final String filePath = "src/infrastructure/persistance/data/pictures.txt";

    /**
     * Reads a picture from the credentials file by their id.
     * @param id The id of the picture to be retrieved.
     * @return The picture with the specified id or null if not found.
     */
    public static Picture findById(UUID id) {
        String stringId = id.toString();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(stringId)) {
                    // User entry found, parse the line and construct a User object
                    return parsePictureFromLine(line);
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
     * Parses a Picture object from a line in the credentials file.
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

    public static List<Picture> findPicturesFromUser(User user) {
        String username = user.getUsername();
        List<Picture> pictures = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Picture picture =parsePictureFromUser(line, username);
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
