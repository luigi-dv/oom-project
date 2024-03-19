package src.presentation.components.profile;

import javax.swing.*;
import java.awt.*;
import src.domain.entities.User;
import src.presentation.components.buttons.ButtonComponent;

public class ProfileHeaderPanel extends JPanel {

    /**
     * The current user.
     */
    private final User currentUser;

    /**
     * Constructor for the ProfileHeaderPanel.
     * @param currentUser The current user.
     */
    public ProfileHeaderPanel(User currentUser) {
        this.currentUser = currentUser;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Top, left, bottom, right padding
        JPanel northPanel = new JPanel(new GridLayout(1, 2));
        northPanel.add(createProfileImage());
        northPanel.add(createStatsPanel());
        add(northPanel, BorderLayout.NORTH);
        add(new BiographyPanel(currentUser), BorderLayout.CENTER);
        add(createButtonsPanel(), BorderLayout.SOUTH);
    }

    /**
     * Creates the buttons panel.
     * @return The buttons panel.
     */
    private JPanel createButtonsPanel() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));
        buttonsPanel.add(createEditProfileButton(), BorderLayout.WEST);
        buttonsPanel.add(Box.createHorizontalStrut(10)); // Add a 10-pixel gap
        buttonsPanel.add(createShareProfileButton(), BorderLayout.EAST);
        return buttonsPanel;
    }

    /**
     * Creates the profile image.
     * @return The profile image.
     */
    private JLabel createProfileImage() {
        int PROFILE_IMAGE_SIZE = 80;
        ImageIcon profileIcon = new ImageIcon(new ImageIcon("resources/storage/images/" + currentUser.getUsername() + ".png")
                .getImage().getScaledInstance(PROFILE_IMAGE_SIZE, PROFILE_IMAGE_SIZE, Image.SCALE_SMOOTH));
        JLabel profileImage = new JLabel(profileIcon);
        profileImage.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return profileImage;
    }

    private JPanel createStatsPanel() {
        return new StatsPanel();
    }

    /**
     * Creates the "Edit Profile" button.
     * @return The "Edit Profile" button.
     */
    private JButton createEditProfileButton() {
        ButtonComponent editProfileButton = new ButtonComponent("Edit Profile", 12, Component.CENTER_ALIGNMENT, "secondary", false);
        editProfileButton.addActionListener(e -> {
            EditProfileDialog editProfileDialog = new EditProfileDialog((JFrame) SwingUtilities.getWindowAncestor(this), currentUser);
            editProfileDialog.setVisible(true);
        });
        return editProfileButton;
    }

    /**
     * Creates the "Share Profile" button.
     * @return The "Share Profile" button.
     */
    private JButton createShareProfileButton() {
        return new ButtonComponent("Share Profile", 12, Component.RIGHT_ALIGNMENT, "secondary", false);
    }
}
