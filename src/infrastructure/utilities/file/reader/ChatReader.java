package src.infrastructure.utilities.file.reader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import src.domain.entities.User;
import src.domain.entities.messages.Chat;
import static src.infrastructure.utilities.file.IFile.FILE_PATH_ROOT;

/**
 * A utility class for reading chats from a JSON file.
 */
public class ChatReader {
    protected static final String FILE_PATH = FILE_PATH_ROOT + "chats.json";

    public static boolean doesFileExist() {
        return Files.exists(Path.of(FILE_PATH));
    }

    /**
     * Finds and retrieves a Picture by its unique identifier.
     *
     * @param id The unique identifier of the picture to be retrieved.
     * @return The Picture with the specified ID or null if not found.
     */
    public static Chat findById(UUID id) {
        try {
            String jsonContent = loadJsonFromFile(FILE_PATH);
            List<Chat> allChats = parseJson(jsonContent);
            for (Chat chat : allChats) {
                if (chat.getId().toString().equals(id.toString())) {
                    return chat;
                }
            }
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
    public static List<Chat> parseJson(String jsonString) {
        List<Chat> chats = new ArrayList<>();

        // Check if the JSON content represents an array with more than one chat
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
            User userA = null;
            User userB = null;

            for (String pair : keyValuePairs) {
                String[] keyValue = pair.split("\\s*:\\s*", 2);
                if (keyValue.length != 2) {
                    continue; // Skip invalid key-value pairs
                }
                String key = keyValue[0].replaceAll("\"", "").trim();
                String value = keyValue[1].replaceAll("\"", "").trim();

                switch (key) {
                    case "id":
                        id = UUID.fromString(value);
                        break;
                    case "userA":
                        userA = new User(value);
                        break;
                    case "userB":
                        userB = new User(value);
                        break;
                }
            }

            // Create and add a new chat object
            chats.add(new Chat(id, userA, userB));
        }

        return chats;
    }

    /**
     * Finds and retrieves a Picture by its unique identifier.
     * @param username The username of the user to find chats for.
     * @return The Picture with the specified ID or null if not found.
     */
    public static List<Chat> findByUsername(String username) {
        try {
            String jsonContent = loadJsonFromFile(FILE_PATH);
            List<Chat> allChats = parseJson(jsonContent);
            List<Chat> userChats = new ArrayList<>();
            for (Chat chat : allChats) {
                if (chat.getUserA().getUsername().equals(username) || chat.getUserB().getUsername().equals(username)) {
                    userChats.add(chat);
                }
            }
            return userChats;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
