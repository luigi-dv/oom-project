package src.presentation.views;

import src.domain.entities.User;
import src.domain.entities.messages.Chat;
import src.domain.entities.messages.Message;
import src.presentation.Router;
import src.presentation.components.messaging.ChatInputComponent;
import src.presentation.components.messaging.MessageComponent;
import src.presentation.components.ui.ScaledIcon;
import src.presentation.components.ui.UserCardComponent;
import src.presentation.controllers.ChatController;
import src.presentation.interfaces.IChatListener;
import src.presentation.interfaces.IMessageSentListener;
import src.presentation.interfaces.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

/**
 * A view that displays a chat.
 */
public class ChatView extends JPanel implements IChatListener {

    private final Router router;
    private final ChatController controller;
    private JScrollPane scrollPane;
    private final Chat chat;
    private List<Message> messages;
    private final User user;
    private final JPanel messagesPanel;
    private final ChatInputComponent chatInputComponent;
    private final UserCardComponent userCard;

    public ChatView(UUID chatID, Router router) {
        this.router = router;
        // Initialize controller and chat
        this.controller = new ChatController();
        this.chat = controller.getChat(chatID);
        this.messages = chat.getMessages();
        this.user = chat.getOtherUser(controller.getAuthenticatedUser());

        setLayout(new BorderLayout());
        setSize(UIConstants.WIDTH, UIConstants.HEIGHT - 50);
        // Create panel for top content
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        // Create user card component
        userCard = new UserCardComponent(user);
        topPanel.add(userCard, BorderLayout.CENTER);

        // Create back button
        ScaledIcon scaledIcon = new ScaledIcon("resources/images/icons/back_icon.png", 10, 10);
        JButton backButton = new JButton(scaledIcon);
        backButton.addActionListener(e -> {
            router.switchTo(UIViews.CHATS);
        });
        topPanel.add(backButton, BorderLayout.WEST);

        // Add top panel to north
        add(topPanel, BorderLayout.NORTH);

        // Create panel for messages
        messagesPanel = new JPanel();
        messagesPanel.setLayout(new BoxLayout(messagesPanel, BoxLayout.Y_AXIS));
        messagesPanel.setBackground(Color.WHITE);

        // Sort messages
        messages.sort(Comparator.comparing(Message::getTimestamp));

        // Add messages to panel
        for (Message message : messages) {
            boolean isOwnMessage = message.getOwner().getUsername().equals(controller.getAuthenticatedUser().getUsername());
            MessageComponent messageComponent = new MessageComponent(message, isOwnMessage);
            messagesPanel.add(messageComponent);
        }

        // Create scroll pane with vertical scrollbar policy
        scrollPane = new JScrollPane(messagesPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Set maximum size for the scroll pane
        scrollPane.setMaximumSize(new Dimension(UIConstants.WIDTH, 200)); // Adjust the height as needed

        // Add scroll pane to center
        add(scrollPane, BorderLayout.CENTER);

        // Create chat input component
        chatInputComponent = new ChatInputComponent(chat);
        chatInputComponent.addMessageSentListener(new IMessageSentListener() {
            @Override
            public void onMessageSent(UUID id) {
                updateMessages();
            }
        });

        // Add chat input component to south with fixed size
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setPreferredSize(new Dimension(UIConstants.WIDTH, 70));
        // bottomPanel.setMaximumSize(new Dimension(UIConstants.WIDTH, 70));
        bottomPanel.add(chatInputComponent, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);


    }

    // Method to update messages
    private void updateMessages() {
        this.messages = chat.getMessages();
        messagesPanel.removeAll();
        // Sort messages
        messages.sort(Comparator.comparing(Message::getTimestamp));
        // Add messages to panel
        for (Message message : messages) {
            boolean isOwnMessage = message.getOwner().getUsername().equals(controller.getAuthenticatedUser().getUsername());
            MessageComponent messageComponent = new MessageComponent(message, isOwnMessage);
            messagesPanel.add(messageComponent);
        }
        messagesPanel.revalidate();
        messagesPanel.repaint();
  
        scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
    }

    /**
     * Called when a new message is received in a chat.
     * @param chatId The ID of the chat.
     */
    @Override
    public void onNewMessage(UUID chatId) {
        if (chat.getId().equals(chatId)) {
            updateMessages();
        }
    }
}
