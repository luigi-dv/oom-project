package src.presentation.views;

import src.presentation.Router;
import src.presentation.components.messaging.ChatPreviewComponent;

import javax.swing.*;
import java.awt.*;

/**
 * A view that displays a list of chats.
 */
public class ChatsView extends JPanel {
    private final Router router;
    private final JList<ChatPreviewComponent> chatsList;

    /**
     * Creates a new ChatsView.
     */
    public ChatsView(Router router) {
        this.router = router;
        JList<ChatPreviewComponent> chatsList = new JList<>(); // Create a new JList
        this.chatsList = chatsList;
        JScrollPane scrollPane = new JScrollPane(chatsList); // Wrap the JList in a JScrollPane
        setLayout(new BorderLayout()); // Use BorderLayout for the JPanel
        add(createTitlePanel(), BorderLayout.PAGE_START); // Add title panel at the top
        add(scrollPane, BorderLayout.CENTER); // Add scrollable chat list in the center
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
