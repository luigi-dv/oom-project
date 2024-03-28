package src.presentation.controllers;

import java.util.List;
import java.util.UUID;

import src.application.providers.SessionProvider;
import src.domain.entities.User;
import src.domain.entities.messages.Chat;
import src.application.services.ChatService;
import src.domain.entities.messages.Message;
import src.presentation.Router;


public class ChatsController {
    private final Router router;
    private final ChatService chatService;
    private final SessionProvider sessionProvider;

    /**
     * Initializes a new instance of the ChatsController class.
     */
    public ChatsController(Router router) {
        this.router = router;
        this.chatService = new ChatService();
        this.sessionProvider = SessionProvider.getInstance();
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

    /**
     * Retrieve the last message of a chat.
     */
    public Message getLastMessage(Chat chat) {
        return chatService.getLastMessage(chat);
    }

    /**
     * Retrieves the authenticated user.
     * @return The authenticated user.
     */
    public User getAuthenticatedUser() {
        return sessionProvider.getAuthenticatedUser();
    }
}
