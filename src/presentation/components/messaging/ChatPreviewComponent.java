package src.presentation.components.messaging;

import src.domain.entities.User;
import src.domain.entities.messages.Chat;
import src.domain.entities.messages.Message;
import src.presentation.components.ui.AvatarImagePanel;
import src.presentation.interfaces.IChatsListener;
import src.presentation.interfaces.UIConstants;
import src.presentation.utils.TimeAgoFormatter;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * A component that displays a preview of a message.
 */
public class ChatPreviewComponent extends JPanel {
    private final Chat chat;
    private final User user;
    private final Message preview;
    private IChatsListener listener;

    /**
     * Creates a new ChatPreviewComponent.
     *
     * @param chat The chat to display a preview of.
     */
    public ChatPreviewComponent(User authenticatedUser, Chat chat, Message lastMessage) {
        this.chat = chat;
        // Select the user that is not the authenticated user
        this.user = chat.getOtherUser(authenticatedUser);
        // Get the most recent message in the chat
        this.preview = lastMessage;
        initializePanel();
        addMouseListener();
    }

    /**
     * Sets the user search listener.
     * @param listener The listener to set.
     */
    public void setChatLister(IChatsListener listener) {
        this.listener = listener;
    }

    /**
     * Initializes the panel.
     */
    private void initializePanel() {
        removeAll();
        setBackground(Color.WHITE);
        setMaximumSize(new Dimension(UIConstants.WIDTH, 70));
        setPreferredSize(new Dimension(UIConstants.WIDTH, 70));
        // Choose an appropriate layout manager, such as FlowLayout or BoxLayout
        setLayout(new FlowLayout(FlowLayout.LEFT)); // Align components to the left

        // Add Padding
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Add avatar panel
        JPanel avatarPanel = createAvatarPanel();
        add(avatarPanel, BorderLayout.WEST);

        // Add message preview panel
        JPanel messagePreviewPanel = createMessagePreviewPanel();
        add(messagePreviewPanel, BorderLayout.CENTER);
    }

    /**
     * Adds a mouse listener to the component.
     */
    private void addMouseListener() {
        addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackgroundAndChildren(Color.LIGHT_GRAY);
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                listener.displayChat(ChatPreviewComponent.this, chat.getId());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackgroundAndChildren(Color.WHITE);
            }
        });
    }

    /**
     * Sets the background color of the panel and its children.
     * @param color The color to set the background to.
     */
    private void setBackgroundAndChildren(Color color) {
        setBackground(color);
        for (Component component : getComponents()) {
            if (component instanceof JPanel) {
                ((JPanel) component).setBackground(color);
            }
        }
    }


    /**
     * Creates a panel that displays the user's avatar.
     * @return A panel that displays the user's avatar.
     */
    private JPanel createAvatarPanel() {
        JPanel avatarPanel = new JPanel(new BorderLayout());
        avatarPanel.setBackground(Color.WHITE);
        System.out.println(user.getProfilePicturePath());
        AvatarImagePanel avatarImagePanel = new AvatarImagePanel(user.getProfilePicturePath(), 50, 50);
        avatarPanel.add(avatarImagePanel, BorderLayout.CENTER);
        avatarPanel.setMaximumSize(new Dimension(50, 50));
        return avatarPanel;
    }

    /**
     * Creates a panel that displays a preview of the message.
     * @return A panel that displays a preview of the message.
     */
    private JPanel createMessagePreviewPanel() {
        JPanel messagePreviewPanel = new JPanel(new BorderLayout());
        messagePreviewPanel.setBackground(Color.WHITE);
        // Add username
        JLabel usernameLabel = new JLabel(user.getUsername());
        usernameLabel.setForeground(Color.BLACK); // Set username color to black
        usernameLabel.setMaximumSize(usernameLabel.getPreferredSize());
        messagePreviewPanel.add(usernameLabel, BorderLayout.NORTH);

        // Truncate message content if longer than 25 characters
        if (preview == null) {
            return messagePreviewPanel;
        }
        String truncatedContent = preview.getContent().length() > 25 ?
                preview.getContent().substring(0, 25) + "..." : preview.getContent();

        // Add truncated message content
        JLabel messageContentLabel = new JLabel(truncatedContent);
        messageContentLabel.setForeground(Color.GRAY); // Set message content color to gray
        messageContentLabel.setMaximumSize(messageContentLabel.getPreferredSize());
        messagePreviewPanel.add(messageContentLabel, BorderLayout.CENTER);

        // Add timestamp (if available)
        if (preview.getTimestamp() != null) {
            JLabel timestampLabel = new JLabel(TimeAgoFormatter.format(preview.getTimestamp()));
            timestampLabel.setForeground(Color.GRAY); // Set timestamp color to gray
            timestampLabel.setFont(timestampLabel.getFont().deriveFont(Font.PLAIN, 10));
            timestampLabel.setMaximumSize(timestampLabel.getPreferredSize());
            messagePreviewPanel.add(timestampLabel, BorderLayout.SOUTH);
        }

        return messagePreviewPanel;
    }
}
