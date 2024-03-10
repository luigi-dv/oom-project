package src.infrastructure.utilities.file.reader;

import java.util.List;
import java.util.UUID;
import java.io.FileReader;
import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.io.BufferedReader;
import src.domain.entities.Like;
import src.domain.entities.User;
import src.domain.entities.Comment;
import src.domain.entities.Picture;
import src.domain.interfaces.ILikeable;
import src.infrastructure.utilities.file.IFile;

/**
 * Utility class for reading likes from a file.
 * Implements the IFile interface for generic file reading functionality.
 *
 * @param <T> Type parameter representing the likeable entity.
 */
public class LikeReader<T extends ILikeable> implements IFile {

    /**
     * Path to the file storing likes.
     */
    private static final String FILE_PATH = FILE_PATH_ROOT + "likes.txt";

    /**
     * Checks if the likes file exists.
     *
     * @return True if the likes file exists, false otherwise.
     */
    public boolean doesFileExist() {
        return Files.exists(Path.of(FILE_PATH));
    }

    /**
     * Retrieves a list of likes associated with a specific post.
     *
     * @param id The unique identifier of the post.
     * @return The list of likes associated with the specified post.
     */
    public List<Like<T>> getLikesFromPost(UUID id) {
        String stringId = id.toString();
        List<Like<T>> likes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("picture" + ":" + stringId) || line.startsWith("comment" + ":" + stringId)) {
                    likes.add(parseLikeFromLine(line));
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle IOException appropriately
        } catch (Exception e) {
            e.printStackTrace(); // Handle other exceptions appropriately
        }

        return likes;
    }

    /**
     * Parses a Like object from a line in the likes file.
     *
     * @param line The line to parse.
     * @return The Like object parsed from the line.
     */
    @SuppressWarnings("unchecked")
    private Like<T> parseLikeFromLine(String line) {
        String[] parts = line.split(":");
        String stringId = parts[1];
        String username = parts[2];
        String stringContentId = parts[3];

        UUID id = UUID.fromString(stringId);
        UUID contentId = UUID.fromString(stringContentId);
        User user = new User(username);

        // TODO: fix this
        ILikeable content;
        if (parts[0].equals("picture")) {
            content = new Picture(contentId);
        } else {
            content = new Comment(contentId);
        }

        return new Like<T>(id, user, (T) content);
    }
}
