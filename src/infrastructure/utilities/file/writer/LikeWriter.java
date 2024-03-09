package src.infrastructure.utilities.file.writer;

import java.io.FileWriter;
import java.io.BufferedWriter;

import src.domain.entities.User;
import src.domain.interfaces.ILikeable;
import src.infrastructure.utilities.file.IFile;

/**
 * Utility class for writing and managing Like entities in a file.
 * Implements the IFileWriter interface for generic file writing functionality.
 *
 * @param <T> The type of entity that can be liked, implementing the ILikeable interface.
 */
public class LikeWriter<T extends ILikeable> implements IFile {

    /**
     * Path to the file storing Like entities.
     */
    private final String FILE_PATH = FILE_PATH_ROOT + "like.txt";

    /**
     * Writes a Like entity to the file.
     *
     * @param like The Like entity to be written.
     * @return The Like entity that was written to the file.
     */
    public src.domain.entities.Like<T> writeToFile(src.domain.entities.Like<T> like) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            // Write the string representation of the Like entity to the file
            writer.write(like.toString());
            writer.newLine();
            return like;
        } catch (Exception e) {
            // TODO: Handle exceptions appropriately (e.g., logging, throwing custom exception)
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Updates the Like entity in the file (Not implemented).
     *
     * @param user The User entity associated with the Like entity.
     * @return The updated Like entity.
     */
    public src.domain.entities.Like<T> updateEntry(User user) {
        // TODO: Implement update entry (search line with the username, update the line, save the file)
        return null;
    }

    /**
     * Deletes the Like entity from the file (Not implemented).
     *
     * @param user The User entity associated with the Like entity.
     * @return The deleted Like entity.
     */
    public src.domain.entities.Like<T> deleteEntry(User user) {
        // TODO: Implement delete entry (search line with the username, delete the line, save the file)
        return null;
    }
}
