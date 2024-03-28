package src.domain.entities.messages;

import src.domain.entities.User;
import src.domain.services.messages.SerializeMessage;

import java.time.LocalDateTime;
import java.util.UUID;

public class Message {
    private final UUID id;
    private final UUID chatId;
    private final User owner;
    private String content;
    private final LocalDateTime timestamp;

    public Message(UUID chatId, User owner, String content) {
        this.id = UUID.randomUUID();
        this.chatId = chatId;
        this.owner = owner;
        this.content = content;
        this.timestamp = LocalDateTime.now();; // Timestamp set to current time
    }

    public Message(UUID id, UUID chatId, User owner, String content, LocalDateTime timestamp) {
        this.id = id;
        this.chatId = chatId;
        this.owner = owner;
        this.content = content;
        this.timestamp = timestamp;
    }

    /**
     * Gets the unique identifier for the message.
     * @return The unique identifier for the message
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the unique identifier for the chat.
     * @return The unique identifier for the chat
     */
    public UUID getChatId() {
        return chatId;
    }

    /**
     * Gets the writer of the message.
     * @return The writer of the message
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Gets the content of the message.
     * @return The content of the message
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the message.
     * @param content The content of the message
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the timestamp of the message.
     * @return The timestamp of the message
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Converts the message to a JSON string.
     * @return The message as a JSON string
     */
    public String toJsonString() {
        return SerializeMessage.toJsonString(this);
    }

}
