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
        try {
            // Open the file in read mode and read all the content
            StringBuilder contentBuilder = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    contentBuilder.append(line);
                }
            }

            // Check if the last character is a closing square bracket
            if (!contentBuilder.isEmpty() && contentBuilder.charAt(contentBuilder.length() - 1) == ']') {
                // If it is, remove it
                contentBuilder.setLength(contentBuilder.length() - 1);
            }

            // If the file is not empty, append a comma to the end of the file
            if (!contentBuilder.isEmpty()) {
                contentBuilder.append(",");
            }

            // Append the new message to the end of the file
            message.setContent(Crypter.StringToEncryptedString(message.getContent()));
            contentBuilder.append(message.toJsonString());

            // Append a closing square bracket to the end of the file
            contentBuilder.append("]");

            // Write the updated content to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                writer.write(contentBuilder.toString());
            }

            return message;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
