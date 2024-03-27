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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static src.infrastructure.utilities.file.IFile.FILE_PATH_ROOT;

/**
 * A utility class for reading messages from a file.
 */
public class MessageReader {
    protected static final String FILE_PATH = FILE_PATH_ROOT + "messages.json";

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
     * Retrieves all messages from the file.
     *
     * @param chatId The chat Unique Identifier.
     * @return A list of messages from the chat
     */
    public static List<Message> getByChatId(UUID chatId) {
        List<Message> filteredMessages = new ArrayList<>();
        try {
            String jsonContent = loadJsonFromFile(FILE_PATH);
            List<Message> allMessages = parseJson(jsonContent);
            for (Message message : allMessages) {
                if (message.getChatId().equals(chatId)) {
                    filteredMessages.add(message);
                }
            }
            return filteredMessages;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Loads a JSON string from a file.
     *
     * @param filePath The path to the file to load the JSON string from.
     * @return The JSON string loaded from the file.
     * @throws IOException If an I/O error occurs.
     */
    public static String loadJsonFromFile(String filePath) throws IOException {
        // Read all bytes from the file and convert them to a string
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));
        return new String(bytes);
    }


    /**
     * Parses a JSON string into a list of Message objects.
     *
     * @param jsonString The JSON string to parse.
     * @return A list of Message objects parsed from the JSON string.
     */
    public static List<Message> parseJson(String jsonString) {
        List<Message> messages = new ArrayList<>();
        // Check if the JSON content represents an array with more than one message
        if (jsonString.startsWith("[") && jsonString.endsWith("]")) {
            // Remove leading and trailing square brackets from the JSON string
            jsonString = jsonString.substring(1, jsonString.length() - 1);
        }

        // Split the JSON string into individual JSON objects
        String[] jsonObjects = jsonString.split("\\}\\s*,\\s*");

        for (String jsonObject : jsonObjects) {
            // Remove leading and trailing braces from each JSON object
            jsonObject = jsonObject.trim().replaceAll("[{}]", "");

            // Split the JSON object into key-value pairs
            String[] keyValuePairs = jsonObject.split("\\s*,\\s*");

            UUID id = null;
            UUID chatId = null;
            User owner = null;
            String content = null;
            LocalDateTime timestamp = null;

            for (String pair : keyValuePairs) {
                String[] keyValue = pair.split("\\s*:\\s*", 2);
                if (keyValue.length != 2) {
                    continue; // Skip invalid key-value pairs
                }
                String key = keyValue[0].replaceAll("\"", "");
                String value = keyValue[1].replaceAll("\"", "").trim();

                switch (key) {
                    case "id":
                        id = UUID.fromString(value);
                        break;
                    case "chatId":
                        chatId = UUID.fromString(value);
                        break;
                    case "owner":
                        owner = new User(value);
                        break;
                    case "content":
                        content = value;
                        break;
                    case "timestamp":
                        timestamp = LocalDateTime.parse(value);
                        break;
                }
            }

            // Create and add a new Message object
            messages.add(new Message(id, chatId, owner, content, timestamp));
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
        String[] parts = line.split(";");
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
