package src.presentation.controllers;

import src.application.providers.SessionProvider;
import src.application.services.ChatService;
import src.application.services.MessageService;
import src.domain.entities.User;
import src.domain.entities.messages.Chat;
import src.domain.entities.messages.Message;

import java.util.UUID;

public class ChatController {
    private final ChatService chatService;
    private final MessageService messageService;

    private final SessionProvider sessionProvider;

    /**
     * Initializes a new instance of the ChatController class.
     */
    public ChatController() {
        this.chatService = new ChatService();
        this.messageService = new MessageService();
        this.sessionProvider = SessionProvider.getInstance();
    }

    /**
     * Load a chat by its ID.
     * @param chatID The ID of the chat to load.
     */
    public Chat getChat(UUID chatID) {
        return chatService.getChat(chatID);
    }

    /**
     * Get the authenticated user.
     * @return The authenticated user.
     */
    public User getAuthenticatedUser() {
        return sessionProvider.getAuthenticatedUser();
    }
}
