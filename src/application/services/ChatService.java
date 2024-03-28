package src.application.services;

import java.util.List;
import java.util.UUID;
import src.application.providers.SessionProvider;
import src.domain.entities.messages.Chat;
import src.domain.entities.messages.Message;
import src.infrastructure.repositories.ChatRepository;
import src.infrastructure.repositories.MessageRepository;

public class ChatService {

    private final ChatRepository repository;
    private final MessageRepository messageRepository;

    private final SessionProvider sessionProvider;

    /**
     * Initializes a new instance of the ChatService class.
     */
    public ChatService() {
        repository = new ChatRepository();
        messageRepository = new MessageRepository();
        sessionProvider = SessionProvider.getInstance();
    }

    /**
     * Retrieves a chat by its unique identifier. It also retrieves all messages for the chat.
     * @param chatId The chat unique identifier.
     * @return The chat with the specified ID or null if not found.
     */
    public Chat getChat(UUID chatId) {
        List<Message> messages = messageRepository.findByChatId(chatId);
        Chat chat = repository.getChat(chatId);
        chat.setMessages(messages);
        return chat;
    }

    /**
     * Retrieves all chats for the authenticated user.
     * @return A list of chats for the authenticated user.
     */
    public List<Chat> getUserChats() {
        String username = sessionProvider.getAuthenticatedUser().getUsername();
        return repository.getUserChats(username);
    }

    /**
     * Retrieve the last message of a chat.
     */
    public Message getLastMessage(Chat chat) {
        return messageRepository.getLastMessage(chat.getId());
    }

    public void saveChat(Chat chat) {
        repository.saveChat(chat);
    }
}
