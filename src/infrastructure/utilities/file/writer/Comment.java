package src.infrastructure.utilities.file.writer;

import src.infrastructure.utilities.file.IFile;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * Utility class responsible for writing comments to a file.
 * Implements the IFileWriter interface for generic file writing functionality.
 */
public class Comment implements IFile {

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
    public static src.domain.entities.Comment writeToFile(src.domain.entities.Comment comment) {
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
