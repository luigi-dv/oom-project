package src.application.services;

import src.application.providers.SessionProvider;
import src.domain.entities.User;
import src.domain.entities.messages.Message;
import src.infrastructure.repositories.MessageRepository;

import java.util.List;
import java.util.UUID;

/**
 * The service responsible for managing messages.
 */
public class MessageService {

    /**
     * The repository used to interact with the data source.
     */
    private final MessageRepository repository;

    /**
     * Initializes a new instance of the MessageService class.
     */
    public MessageService() {
        repository = new MessageRepository();
    }

    /**
     * Retrieves all messages from the desired chat.
     * @param chatId The chat Unique Identifier.
     * @return A list of messages from the chat
     */
    public List<Message> getChatMessages(UUID chatId) {
        return repository.findByChatId(chatId);
    }

    /**
     * Retrieves a message by its unique identifier.
     * @param id The unique identifier of the message to be retrieved.
     * @return The message with the specified ID or null if not found.
     */

    public Message getMessage(UUID id) {
        return repository.findById(id);
    }

    /**
     * Saves a message to the repository.
     * @param message The message to be saved.
     * @return The saved message.
     */
    public Message saveMessage(Message message) throws Exception {
        User authenticatedUser = SessionProvider.getInstance().getAuthenticatedUser();
        if(message.getOwner().getUsername() == authenticatedUser.getUsername()) {
            return repository.save(message);
        }
        else {
            throw new Exception("User not authenticated");
        }

    }
}
