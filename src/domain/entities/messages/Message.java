package src.domain.entities.messages;

import src.domain.entities.User;
import java.time.LocalDateTime;
import java.util.UUID;

public class Message {
    private final UUID id;
    private final UUID chatId;
    private final User writer;
    private String content;
    private final LocalDateTime timestamp;

    public Message(UUID chatId, User user, String content) {
        this.id = UUID.randomUUID();
        this.chatId = chatId;
        this.writer = user;
        this.content = content;
        this.timestamp = LocalDateTime.now();; // Timestamp set to current time
    }

    public Message(UUID id, UUID chatId, User writer, String content, LocalDateTime timestamp) {
        this.id = id;
        this.chatId = chatId;
        this.writer = writer;
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
    public User getWriter() {
        return writer;
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

    @Override
    public String toString() {
       return id.toString() + ":" + chatId.toString() + ":" + writer.getUsername() + ":" + content + ":" + timestamp.toString();
    }
}
