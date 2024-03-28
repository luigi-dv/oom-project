package src.infrastructure.utilities.file.writer;

import java.io.*;

import src.domain.entities.messages.Message;
import src.infrastructure.utilities.Crypter;

import static src.infrastructure.utilities.file.IFile.FILE_PATH_ROOT;

/**
 * A utility class for writing messages to a JSON file.
 */
public class MessageWriter {
    protected static final String FILE_PATH = FILE_PATH_ROOT + "messages.json";

    /**
     * Writes the message to the messages file.
     * @param message The message to be saved.
     * @return The message with the saved content.
     */
    public static Message writeToFile(Message message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            message.setContent(Crypter.StringToEncryptedString(message.getContent()));
            String jsonMessage = message.toJsonString();
            writer.write(jsonMessage);
            writer.newLine();
            return message;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
