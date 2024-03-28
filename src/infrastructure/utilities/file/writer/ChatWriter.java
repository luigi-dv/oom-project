package src.infrastructure.utilities.file.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import src.domain.entities.messages.Chat;
import static src.infrastructure.utilities.file.IFile.FILE_PATH_ROOT;

/**
 * A utility class for writing chats to a JSON file.
 */
public class ChatWriter {
    protected static final String FILE_PATH = FILE_PATH_ROOT + "chats.json";

    /**
     * Writes the chats to the chats file.
     * @param chat The chat to be saved.
     * @return The chat with the saved content.
     */
    public static Chat writeToFile(Chat chat) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String jsonMessage = chat.toJsonString();
            writer.write(jsonMessage);
            writer.newLine();
            return chat;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
