package src.infrastructure.utilities.filewriter;

import src.domain.entities.Comment;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class CommentWriter {
    private static final String commentFileWrite = "src/infrastructure/persistance/data/comments.txt";
    public static Comment writeToFile(Comment comment) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(commentFileWrite, true))) {
            writer.write(comment.toString());
            writer.newLine();
            return comment;
        } catch (Exception e) {
            // TODO: Catch exception
            e.printStackTrace();
            return null;
        }

    }

}
