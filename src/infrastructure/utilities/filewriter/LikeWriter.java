package src.infrastructure.utilities.filewriter;

import src.domain.entities.ILikeable;
import src.domain.entities.Like;
import src.domain.entities.User;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class LikeWriter<T extends ILikeable> {

    private final String credentialsFilePath = "src/infrastructure/persistance/date/like.txt";

    /**
     * Writes a like to the file
     * @param like the like to be written
     * @return the like that was written
     */
    public Like<T> writeToFile(Like<T> like) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(credentialsFilePath, true))) {
            writer.write(like.toString());
            writer.newLine();
            return like;
        } catch (Exception e) {
            // TODO: Catch exception
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Updates the user credentials in the credentials file.
     * @param user The User entity to update
     * @return The updated User entity
     */
    public Like<T> updateEntry(User user) {
        // TODO: Implement update credentials (search line with the username, update the line, save the file)
        return null;
    }

    /**
     * Deletes the user credentials from the credentials file.
     * @param user The User entity to delete
     * @return The deleted User entity
     */
    public Like<T> deleteEntry(User user) {
        // TODO: Implement delete credentials (search line with the username, delete the line, save the file)
        return null;
    }
}
