package src.presentation.views;

import src.domain.entities.messages.Chat;
import src.presentation.Router;
import src.presentation.components.messaging.ChatPreviewComponent;
import src.presentation.controllers.ChatsController;

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
    private final JList<ChatPreviewComponent> chatsList;

    /**
     * Creates a new ChatsView.
     */
    public ChatsView(Router router) {
        this.router = router;
        this.controller = new ChatsController();
        this.chats = getChats();
        this.chatsList = createChatsList();
        JScrollPane scrollPane = new JScrollPane(chatsList); // Wrap the JList in a JScrollPane
        setLayout(new BorderLayout()); // Use BorderLayout for the JPanel
        add(createTitlePanel(), BorderLayout.PAGE_START); // Add title panel at the top
        add(scrollPane, BorderLayout.CENTER); // Add scrollable chat list in the center
    }

    /**
     * Retrieves the chats for the authenticated user.
     * @return A list of chats for the authenticated user.
     */
    private List<Chat> getChats() {
        return controller.getUserChats();
    }

    private JList<ChatPreviewComponent> createChatsList() {
        DefaultListModel<ChatPreviewComponent> model = new DefaultListModel<>();
        if(!chats.isEmpty()) {
            System.out.println("Chats: " + chats.size());
            for (Chat chat : chats) {
                chat.setMessages(controller.getChat(chat.getId()).getMessages());
                ChatPreviewComponent chatPreview = new ChatPreviewComponent(chat);
                model.addElement(chatPreview);
            }
            JList<ChatPreviewComponent> chatsList = new JList<>(model);
            chatsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            chatsList.setLayoutOrientation(JList.VERTICAL);
            chatsList.setVisibleRowCount(-1);
        }
        else{
            JLabel noChatsLabel = new JLabel("No chats available.");
            add(noChatsLabel, BorderLayout.CENTER);
        }

        return chatsList;
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
