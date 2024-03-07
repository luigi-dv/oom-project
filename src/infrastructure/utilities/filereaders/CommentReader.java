package src.infrastructure.utilities.filereaders;

import src.domain.entities.Comment;
import src.domain.entities.Picture;
import src.domain.entities.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommentReader {

    private static final String credentialsFilePath = "src/infrastructure/persistance/date/comments.txt";

    public static List<Comment> getCommentsByPostId(UUID id) {
        List<Comment> comments = new ArrayList<>();
        String stringId = id.toString();

        try (BufferedReader reader = new BufferedReader(new FileReader(credentialsFilePath))) {
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
