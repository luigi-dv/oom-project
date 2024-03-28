package src.infrastructure.utilities.file.writer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
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
        
        String jsonMessage = chat.toJsonString();
        appendToJsonArray(jsonMessage);
        return chat;
    }
    
    private static boolean appendToJsonArray(String newJson) {
        StringBuilder jsonContent = new StringBuilder();
        try {
            // Read existing content
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line.trim());
            }
            reader.close();

            // Remove the last closing bracket
            if (jsonContent.length() > 0 && jsonContent.charAt(jsonContent.length() - 1) == ']') {
                jsonContent.deleteCharAt(jsonContent.length() - 1);
                // If not the first object, append a comma
                if (jsonContent.length() > 1) {
                    jsonContent.append(",");
                }
            }

            // Append the new JSON object and close the array
            jsonContent.append(newJson).append("]");

            // Overwrite the file with the updated content
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, false));
            writer.write(jsonContent.toString());
            writer.close();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
