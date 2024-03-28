package src.presentation.controllers;

import src.application.providers.SessionProvider;
import src.application.services.ChatService;
import src.application.services.MessageService;
import src.domain.entities.messages.Message;

import java.util.UUID;

public class ChatInputController {

    private final MessageService messageService;
    private final ChatService chatService;
    private final SessionProvider sessionProvider;

    public ChatInputController() {
        this.messageService = new MessageService();
        this.chatService = new ChatService();
        this.sessionProvider = SessionProvider.getInstance();
    }

    /**
     * Sends a message to a chat.
     * @param messageContent The message content to be sent.
     * @param chatId The chat Unique Identifier.
     */
    public void sendMessage(UUID chatId, String messageContent) throws Exception {
        Message message = new Message(chatId, sessionProvider.getAuthenticatedUser(), messageContent);
        messageService.saveMessage(message);
    }
}
