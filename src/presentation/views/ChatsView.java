package src.presentation.views;

import java.awt.*;
import javax.swing.*;
import java.util.List;
import java.util.UUID;
import src.presentation.Router;
import src.domain.entities.messages.Chat;
import src.domain.entities.messages.Message;
import src.presentation.controllers.ChatsController;
import src.presentation.components.messaging.ChatPreviewComponent;
import src.presentation.interfaces.IChatsListener;
import src.presentation.interfaces.UIConstants;

/**
 * A view that displays a list of chats.
 */
public class ChatsView extends JPanel implements IChatsListener{
    private final Router router;

    private final List<Chat> chats;

    private final ChatsController controller;
    private final JScrollPane chatsList;

    /**
     * Creates a new ChatsView.
     */
    public ChatsView(Router router) {
        this.router = router;
        this.controller = new ChatsController(router);
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
                Message lastMessage = controller.getLastMessage(chat);
                ChatPreviewComponent chatPreview = new ChatPreviewComponent(controller.getAuthenticatedUser(), chat, lastMessage);
                chatPreview.setAlignmentX(Component.LEFT_ALIGNMENT); // Align chat preview components to the left
                chatPreview.setChatLister(this);
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

    /**
     * Displays the chat with the specified ID.
     * @param chat The panel to display the chat in.
     * @param chatId The ID of the chat to display.
     */
    @Override
    public void displayChat(JPanel chat, UUID chatId) {
        JPanel panel = new ChatView(chatId, router);
        removeAll();
        add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    /**
     * Called when a new message is received in a chat.
     * @param chatId The ID of the chat.
     */
    @Override
    public void onNewMessage(UUID chatId) {
        // Find the chat with the given ID
        for (Chat chat : chats) {
            if (chat.getId().equals(chatId)) {
                // Get the last message of the chat
                Message lastMessage = controller.getLastMessage(chat);
                // Update the chat preview
                ChatPreviewComponent chatPreview = new ChatPreviewComponent(controller.getAuthenticatedUser(), chat, lastMessage);
                chatPreview.setAlignmentX(Component.LEFT_ALIGNMENT);
                chatPreview.setChatLister(this);
                // Add the updated chat preview to the chats list
                chatsList.add(chatPreview);
                break;
            }
        }
        // Re-render the chats list
        revalidate();
        repaint();
    }
}
