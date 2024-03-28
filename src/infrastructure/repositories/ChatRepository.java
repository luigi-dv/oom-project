package src.infrastructure.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import src.domain.entities.messages.Chat;
import src.domain.entities.messages.Message;
import src.infrastructure.utilities.file.reader.ChatReader;


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
}
