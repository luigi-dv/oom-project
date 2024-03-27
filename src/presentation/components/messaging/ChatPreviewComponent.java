package src.presentation.components.messaging;

import src.domain.entities.User;
import src.domain.entities.messages.Chat;
import src.domain.entities.messages.Message;
import src.presentation.components.ui.AvatarImagePanel;

import javax.swing.*;
import java.awt.*;

/**
 * A component that displays a preview of a message.
 */
public class ChatPreviewComponent extends JPanel {
    private final User user;
    private final Message preview;

    public ChatPreviewComponent(Chat chat) {
        this.user = chat.getUser1();
        this.preview = chat.getMostRecentMessage();
        setLayout(new BorderLayout());
        add(createAvatarPanel(), BorderLayout.WEST);
        add(new JLabel(preview.getContent()), BorderLayout.CENTER);
    }

    private JPanel createAvatarPanel() {
        JPanel avatarPanel = new JPanel(new BorderLayout());
        AvatarImagePanel avatarImagePanel = new AvatarImagePanel(user.getProfilePicturePath(), 50, 50);
        avatarPanel.add(avatarImagePanel, BorderLayout.CENTER);
        return avatarPanel;
    }
}
