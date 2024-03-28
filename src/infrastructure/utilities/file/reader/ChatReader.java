package src.infrastructure.utilities.file.reader;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import src.domain.entities.User;
import src.domain.entities.messages.Chat;

import static src.infrastructure.utilities.file.IFile.FILE_PATH_ROOT;

public class ChatReader {
    protected static final String FILE_PATH = FILE_PATH_ROOT + "chats.txt";

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
        String stringId = id.toString();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(stringId)) {
                    // Picture entry found, parse the line and construct a Picture object
                    return parseChat(line);
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
     * @param username The username of the user.
     * @return A list of messages from the chat
     */
    public static List<Chat> findByUsername(String username) {
        List<Chat> chats = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(username)) {
                    // Picture entry found, parse the line and construct a Picture object
                    chats.add(parseChat(line));
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle IOException appropriately
        } catch (Exception e) {
            e.printStackTrace(); // Handle other exceptions appropriately
        }
        // If the message is not found, return null
        return chats;
    }


    /**
     * Parses a Picture object from a line in the credentials file.
     *
     * @param line The line to parse.
     * @return The Picture object parsed from the line.
     */
    private static Chat parseChat(String line) {
        String[] parts = line.split(":");
        // Message ID
        String stringId = parts[0];
        UUID uuid = UUID.fromString(stringId);
        // First User
        String firstUserString = parts[1];
        User firstUser = new User(firstUserString);
        // Second User
        String secondUserString = parts[2];
        User secondUser = new User(secondUserString);
        return new Chat(uuid, firstUser, secondUser);
    }
}
