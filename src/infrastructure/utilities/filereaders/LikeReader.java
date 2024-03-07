package src.infrastructure.utilities.filereaders;

import src.domain.entities.ILikeable;
import src.domain.entities.Like;
import src.domain.entities.Picture;
import src.domain.entities.User;
import src.domain.entities.Comment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LikeReader<T extends ILikeable> {

    /**
     * Path to the credentials file storing user authentication information.
     */
    protected final String filePath = "src/infrastructure/persistance/data/credentials.txt";

    /**
     * Checks if the credentials file exists.
     * @return True if the credentials file exists, false otherwise.
     */
    public boolean doesFileExist() {
        return Files.exists(Path.of(filePath));
    }

    public ArrayList<Like<T>> getLikesFromPost(UUID id) {
        String stringId = id.toString();
        List<Like<T>> likes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("picture" + ":" + stringId)) {
                    likes.add(parseLikeFromLine(line));
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
    private Like<T> parseLikeFromLine(String line) {
        String[] parts = line.split(":");
        String stringId = parts[1];
        String username = parts[2];
        String stringContentId = parts[3];

        UUID id = UUID.fromString(stringId);
        UUID contentId = UUID.fromString(stringContentId);
        User user = new User(username);

        Like<T> like;
        if (parts[0].equals("picture")) {
            like = new Like(id, user, new Picture(contentId));
        } else {
            like = new Like(id, user, new Comment(contentId));
        }
        return like;
    }

}
