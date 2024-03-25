package src.infrastructure.utilities.file.reader;

import src.domain.entities.User;
import src.domain.entities.messages.Message;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static src.infrastructure.utilities.file.IFile.FILE_PATH_ROOT;

public class MessageReader {
    protected static final String FILE_PATH = FILE_PATH_ROOT + "messages.txt";

    public static boolean doesFileExist() {
        return Files.exists(Path.of(FILE_PATH));
    }

    /**
     * Finds and retrieves a Picture by its unique identifier.
     *
     * @param id The unique identifier of the picture to be retrieved.
     * @return The Picture with the specified ID or null if not found.
     */
    public static Message findById(UUID id) {
        String stringId = id.toString();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(stringId)) {
                    // Picture entry found, parse the line and construct a Picture object
                    return parseMessage(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle IOException appropriately
        } catch (Exception e) {
            e.printStackTrace(); // Handle other exceptions appropriately
        }
        // If the message is not found, return null
        return null;
    }

    /**
     * Retrieves all messages form the desired chat.
     * @param chatId The chat Unique Identifier.
     * @return A list of messages from the chat
     */
    public static List<Message> getByChatId(UUID chatId) {
        List<Message> messages = new ArrayList<>();
        // Jump to the position after : to find the chat ID
        String stringChatId = chatId.toString();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Message message = parseMessage(line);
                if (message.getChatId().toString().equals(stringChatId)) {
                    messages.add(message);
                }
            }

        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        return messages;
    }


    /**
     * Parses a Picture object from a line in the credentials file.
     *
     * @param line The line to parse.
     * @return The Picture object parsed from the line.
     */
    private static Message parseMessage(String line) {
        String[] parts = line.split(":");
        // Message ID
        String stringId = parts[0];
        UUID uuid = UUID.fromString(stringId);
        // Chat ID
        String chatId = parts[1];
        UUID chatUUID = UUID.fromString(chatId);
        // Owner username
        String ownerId = parts[2];
        User ownerUser = new User(ownerId);
        // Content
        String content = parts[3];
        // Timestamp
        String dateTimeString = parts[4];
        LocalDateTime timestamp = LocalDateTime.parse(dateTimeString);

        return new Message(uuid, chatUUID, ownerUser, content, timestamp);
    }
}
