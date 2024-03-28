package src.presentation.controllers;

import java.util.List;
import java.util.UUID;
import src.domain.entities.messages.Chat;
import src.application.services.ChatService;


public class ChatsController {
    private final ChatService chatService;

    /**
     * Initializes a new instance of the ChatsController class.
     */
    public ChatsController() {
        this.chatService = new ChatService();
    }

    /**
     * Retrieves all chats for the authenticated user.
     * @return A list of chats for the authenticated user.
     */
    public List<Chat> getUserChats() {
        return chatService.getUserChats();
    }

    /**
     * Retrieves a chat by its unique identifier.
     * @param chatId The chat unique identifier.
     * @return The chat with the specified ID or null if not found.
     */
    public Chat getChat(UUID chatId) {
        return chatService.getChat(chatId);
    }
}
