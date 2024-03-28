package src.domain.entities.messages;

import src.domain.entities.User;
import src.domain.services.messages.SerializeChat;
import src.domain.services.messages.SerializeMessage;

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
    private final User userA;
    /**
     * The second user in the chat.
     */
    private final User userB;

    /**
     * The messages in the chat.
     */
    private List<Message> messages;

    /**
     * Constructor for new Chat
     * @param userA The first user in the chat
     * @param userB The second user in the chat
     */
    public Chat(User userA, User userB) {
        this.id = UUID.randomUUID();
        this.userA = userA;
        this.userB = userB;
        this.messages = new ArrayList<>();
    }

    /**
     * Constructor for existing Chat
     * @param id The unique identifier for the chat
     * @param userA The first user in the chat
     * @param userB The second user in the chat
     */
    public Chat(UUID id, User userA, User userB) {
        this.id = id;
        this.userA = userA;
        this.userB = userB;
    }

    /**
     * Constructor for existing Chat
     * @param id The unique identifier for the chat
     * @param userA The first user in the chat
     * @param userB The second user in the chat
     * @param messages The messages in the chat
     */
    public Chat(UUID id, User userA, User userB, List<Message> messages) {
        this.id = id;
        this.userA = userA;
        this.userB = userB;
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
    public User getUserA() {
        return userA;
    }

    /**
     * Gets the second user in the chat.
     * @return The second user in the chat
     */
    public User getUserB() {
        return userB;
    }

    /**
     * Gets the other user in the chat.
     * @param user The user to get the other user for
     * @return The other user in the chat
     */
    public User getOtherUser(User user) {
        if (user.getUsername().equals(userA.getUsername())) {
            return userB;
        } else {
            return userA;
        }
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


    /**
     * Converts the chat to a JSON string.
     * @return The chat as a JSON string
     */
    public String toJsonString() {
        return SerializeChat.toJsonString(this);
    }
}
