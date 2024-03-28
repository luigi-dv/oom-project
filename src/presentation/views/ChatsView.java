package src.presentation.views;

import src.domain.entities.messages.Chat;
import src.presentation.Router;
import src.presentation.components.messaging.ChatPreviewComponent;
import src.presentation.controllers.ChatsController;
import src.presentation.interfaces.UIConstants;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * A view that displays a list of chats.
 */
public class ChatsView extends JPanel {
    private final Router router;

    private final List<Chat> chats;

    private final ChatsController controller;
    private final JScrollPane chatsList;

    /**
     * Creates a new ChatsView.
     */
    public ChatsView(Router router) {
        this.router = router;
        this.controller = new ChatsController();
        this.chats = getChats();
        this.chatsList = createChatsList();
        add(createTitlePanel(), BorderLayout.PAGE_START); // Add title panel at the top
        add(chatsList); // Add scrollable chat list in the center
    }

    /**
     * Retrieves the chats for the authenticated user.
     * @return A list of chats for the authenticated user.
     */
    private List<Chat> getChats() {
        return controller.getUserChats();
    }

    /**
     * Creates a JList of ChatPreviewComponents.
     * @return A JList of ChatPreviewComponents.
     */
    private JScrollPane createChatsList() {
        JPanel previewsPanel = new JPanel();
        previewsPanel.setLayout(new BoxLayout(previewsPanel, BoxLayout.Y_AXIS));

        if (!chats.isEmpty()) {
            for (Chat chat : chats) {
                chat.setMessages(controller.getChat(chat.getId()).getMessages());
                ChatPreviewComponent chatPreview = new ChatPreviewComponent(controller.getAuthenticatedUser(), chat);
                chatPreview.setAlignmentX(Component.LEFT_ALIGNMENT); // Align chat preview components to the left
                previewsPanel.add(chatPreview);
            }
        } else {
            JLabel noChatsLabel = new JLabel("No chats available.");
            noChatsLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align label to the left
            previewsPanel.add(noChatsLabel);
        }

        // Wrap the previewsPanel in a JScrollPane
        JScrollPane scrollPane = new JScrollPane(previewsPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        return scrollPane;
    }



    /**
     * Creates a panel with the title of the view.
     * @return The title panel.
     */
    private JPanel createTitlePanel() {
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Messages"); // Title message
        titlePanel.add(titleLabel);
        return titlePanel;
    }
}
