package src.infrastructure.repositories;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

import src.domain.entities.User;
import src.domain.entities.messages.Chat;
import src.infrastructure.utilities.file.reader.ChatReader;
import src.infrastructure.utilities.file.writer.ChatWriter;

/**
 * A repository for chat entities.
 */
public class ChatRepository {

    /**
     * Updates a message.
     * @return The updated message.
     */
    public List<Chat> getUserChats(String username)
    {
        List<Chat> chats = new ArrayList<>();
        if (ChatReader.doesFileExist()) {
            chats = ChatReader.findByUsername(username);
        }
        return chats;
    }

    /**
     * Retrieves a chat by its unique identifier.
     * @param chatId The chat unique identifier.
     * @return The chat with the specified ID or null if not found.
     */
    public Chat getChat(UUID chatId){
        Chat chat = null;
        if (ChatReader.doesFileExist()) {
            chat = ChatReader.findById(chatId);
        }
        return chat;
    }

    /**
     * Retrieves a chat between two users if it exists.
     * @param userA The first user.
     * @param userB The second user.
     */
    public Chat getChatBetweenUsers(User userA, User userB) {
        Chat chat = null;
        if (ChatReader.doesFileExist()) {
            chat = ChatReader.findByUsers(userA.getUsername(), userB.getUsername());
        }
        return chat;
    }

    /**
     * Saves a chat.
     * @param chat The chat to be saved.
     */
    public Chat saveChat(Chat chat){
        return ChatWriter.writeToFile(chat);
    }
}
