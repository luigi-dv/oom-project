package src.infrastructure.utilities.file.writer;

import java.io.FileWriter;
import java.io.BufferedWriter;
import src.domain.entities.Comment;
import src.infrastructure.utilities.file.IFile;

/**
 * Utility class responsible for writing comments to a file.
 * Implements the IFileWriter interface for generic file writing functionality.
 */
public class CommentWriter implements IFile {

    /**
     * Path to the file.
     */
    private static final String FILE_PATH = FILE_PATH_ROOT + "comments.txt";

    /**
     * Writes the given comment to the comments file.
     *
     * @param comment The comment to write to the file.
     * @return The comment that was written to the file.
     */
    public static Comment writeToFile(Comment comment) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(comment.toString());
            writer.newLine();
            return comment;
        } catch (Exception e) {
            // TODO: Handle the exception appropriately (e.g., logging, throwing custom exception)
            e.printStackTrace();
            return null;
        }
    }
}
