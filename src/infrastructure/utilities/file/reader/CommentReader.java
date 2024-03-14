package src.infrastructure.utilities.file.reader;

import java.util.List;
import java.util.UUID;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import src.domain.entities.User;
import src.domain.entities.Comment;
import src.domain.entities.Picture;
import src.infrastructure.utilities.file.IFile;

/**
 * Utility class for reading comments from a file based on post IDs.
 * Implements the IFile interface for generic file reading functionality.
 */
public class CommentReader implements IFile {

    /**
     * Path to the file storing comments.
     */
    private static final String FILE_PATH = FILE_PATH_ROOT + "comments.txt";

    /**
     * Retrieves a list of comments associated with a specific post ID.
     *
     * @param id The post ID to retrieve comments for.
     * @return A list of Comment entities related to the specified post ID.
     */
    public static List<Comment> getCommentsByPostId(UUID id) {
        List<Comment> comments = new ArrayList<>();
        String stringId = id.toString();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Comment comment = parseCommentFromLine(line, stringId);
                if (comment != null) {
                    comments.add(comment);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle IOException appropriately
        } catch (Exception e) {
            e.printStackTrace(); // Handle other exceptions appropriately
        }

        return comments;
    }

    /**
     * Parses a Comment object from a line in the comments file.
     *
     * @param line The line to parse.
     * @param id   The post ID to match while parsing comments.
     * @return The Comment object parsed from the line.
     */
    private static Comment parseCommentFromLine(String line, String id) {
        String[] parts = line.split(":");
        String postId = parts[1];
        if (!postId.equals(id)) {
            return null;
        }
        String commentId = parts[0];
        String username = parts[2];
        String content = parts[3];

        Picture picture = new Picture(UUID.fromString(postId));
        User user = new User(username);

        return new Comment(UUID.fromString(commentId), user, picture, content);
    }
}
