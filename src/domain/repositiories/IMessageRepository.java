package src.domain.repositiories;

import java.util.List;
import java.util.UUID;
import src.domain.entities.messages.Message;

public interface IMessageRepository {
    /**
     * Finds a like by its unique identifier.
     *
     * @param id The unique identifier of the message.
     * @return The like with the specified ID or null if not found.
     */
    Message findById(UUID id);

    /**
     * Saves a new like.
     *
     * @param message The message entity to be saved.
     * @return The saved like entity.
     */
    Message save(Message message);

    /**
     * Finds all message associated with a specific Chat.
     *
     * @param chatId The unique identifier of the Chat.
     * @return A list of messages associated with the specified Chat ID.
     */
    List<Message> findByChatId(UUID chatId);
}
