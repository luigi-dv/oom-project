package src.infrastructure.repositories;

import src.domain.entities.messages.Chat;
import src.domain.entities.messages.Message;
import src.domain.repositiories.IMessageRepository;
import src.infrastructure.utilities.file.reader.MessageReader;
import src.infrastructure.utilities.file.writer.MessageWriter;

import java.util.List;
import java.util.UUID;

public class MessageRepository implements IMessageRepository {

    /**
     * Finds and retrieves a Message by its unique identifier.
     */
    public Message findById(UUID id) {
        if(MessageReader.doesFileExist()) {
            return MessageReader.findById(id);
        }
        // TODO: Not found logging catching
        return null;
    }

    /**
     * Retrieves all messages form the desired chat.
     * @param chatId The chat Unique Identifier.
     * @return A list of messages from the chat
     */
    public List<Message> findByChatId(UUID chatId) {
        if (MessageReader.doesFileExist()) {
            return MessageReader.getByChatId(chatId);
        }
        // TODO: Not found logging catching
        return null;
    }

    /**
     * Creates a new Message.
     *
     * @param message The user to be created.
     * @return The created user entity.
     */
    public Message save(Message message) {
        if(MessageReader.doesFileExist()) {
            return MessageWriter.writeToFile(message);
        }
        // TODO: Not found logging catching
        return null;
    }
}
