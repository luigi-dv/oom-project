package src.infrastructure.utilities.file.writer;

import java.io.FileWriter;
import java.io.BufferedWriter;
import src.domain.aggregate.Follow;
import src.infrastructure.utilities.file.IFile;

/**
 * Utility class for writing Follow entities to a file.
 * Implements the IFileWriter interface for generic file writing functionality.
 */
public class Follower implements IFile {

    /**
     * Path to the file.
     */
    private static final String FILE_PATH = FILE_PATH_ROOT + "follow.txt";

    /**
     * Writes a Follow entity to the follow file.
     *
     * @param follow The Follow entity to write to the file.
     * @return The Follow entity that was written to the file.
     */
    public static Follow writeToFile(Follow follow) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            // Write the string representation of the Follow entity to the file
            writer.write(follow.toString());
            writer.newLine();
            return follow;
        } catch (Exception e) {
            // TODO: Handle exceptions appropriately (e.g., logging, throwing custom exception)
            e.printStackTrace();
            return null;
        }
    }
}
