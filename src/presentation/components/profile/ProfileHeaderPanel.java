package src.presentation.components.profile;

import javax.swing.*;
import java.awt.*;
import src.domain.entities.User;
import src.presentation.components.buttons.ButtonComponent;
import src.presentation.components.ui.AvatarImagePanel;

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
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(createProfileImage(), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        add(createStatsPanel(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(new BiographyPanel(currentUser), gbc);

        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        add(createButtonsPanel(), gbc);
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
    private JPanel createProfileImage() {
        return new AvatarImagePanel(currentUser.getProfilePicturePath(), 80, 80);
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
