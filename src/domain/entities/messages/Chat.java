package src.domain.entities.messages;

import src.domain.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Chat {

    /**
     * The unique identifier for the chat.
     */
    private final UUID id;

    /**
     * The first user in the chat.
     */
    private final User user1;
    /**
     * The second user in the chat.
     */
    private final User user2;

    /**
     * The messages in the chat.
     */
    private List<Message> messages;

    /**
     * Constructor for new Chat
     * @param user1 The first user in the chat
     * @param user2 The second user in the chat
     */
    public Chat(User user1, User user2) {
        this.id = UUID.randomUUID();
        this.user1 = user1;
        this.user2 = user2;
        this.messages = new ArrayList<>();
    }

    /**
     * Constructor for existing Chat
     * @param id The unique identifier for the chat
     * @param user1 The first user in the chat
     * @param user2 The second user in the chat
     */
    public Chat(UUID id, User user1, User user2) {
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
    }

    /**
     * Constructor for existing Chat
     * @param id The unique identifier for the chat
     * @param user1 The first user in the chat
     * @param user2 The second user in the chat
     * @param messages The messages in the chat
     */
    public Chat(UUID id, User user1, User user2, List<Message> messages) {
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
        this.messages = messages;
    }

    /**
     * Gets the unique identifier for the chat.
     * @return The unique identifier for the chat
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the first user in the chat.
     * @return The first user in the chat
     */
    public User getUser1() {
        return user1;
    }

    /**
     * Gets the second user in the chat.
     * @return The second user in the chat
     */
    public User getUser2() {
        return user2;
    }

    /**
     * Adds a message to the chat.
     * @param message The message to add to the chat
     */
    public void addMessage(Message message) {
        this.messages.add(message);
    }

    /**
     * Gets the messages in the chat.
     * @return The messages in the chat
     */
    public List<Message> getMessages() {
        return messages;
    }

    /**
     * Sets the messages in the chat.
     * @param messages The messages in the chat
     */
    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    /**
     * Gets the most recent message in the chat.
     * @return The most recent message in the chat
     */
    public Message getMostRecentMessage() {
        return messages.get(messages.size() - 1);
    }
}
