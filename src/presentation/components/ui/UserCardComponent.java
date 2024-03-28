package src.presentation.components.ui;

import src.domain.entities.User;
import src.presentation.components.ui.AvatarImagePanel;
import src.presentation.interfaces.UIConstants;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * A component that displays a user in a card format.
 */
public class UserCardComponent extends JPanel {
    private final User user;

    /**
     * Creates a new UserCardComponent.
     * @param user The user to display.
     */
    public UserCardComponent(User user) {
        this.user = user;
        initializePanel();
    }

    /**
     * Initializes the panel.
     */
    public void initializePanel() {
        setMaximumSize(new Dimension(UIConstants.WIDTH, 70));
        // Choose an appropriate layout manager, such as FlowLayout or BoxLayout
        setLayout(new FlowLayout(FlowLayout.LEFT)); // Align components to the left

        JPanel imagePanel = new AvatarImagePanel(user.getProfilePicturePath(), 50, 50);
        JLabel usernameLabel = new JLabel(user.getUsername());

        add(imagePanel);
        add(usernameLabel);
    }
}