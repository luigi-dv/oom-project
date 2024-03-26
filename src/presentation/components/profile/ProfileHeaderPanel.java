package src.presentation.components.profile;

import javax.swing.*;
import java.awt.*;
import src.domain.entities.User;
import src.presentation.Router;
import src.presentation.components.buttons.ButtonComponent;
import src.presentation.components.buttons.FollowButton;
import src.presentation.components.ui.AvatarImagePanel;
import src.presentation.controllers.profile.ProfileHeaderPanelController;

public class ProfileHeaderPanel extends JPanel {

    /**
     * The current user.
     */
    private final User currentUser;
    private final Router router;
    private final ProfileHeaderPanelController controller;

    /**
     * Constructor for the ProfileHeaderPanel.
     * @param currentUser The current user.
     */
    public ProfileHeaderPanel(User currentUser, Router router) {
        this.currentUser = currentUser;
        this.router = router;
        this.controller = new ProfileHeaderPanelController(router);

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
        if(!controller.isAuthenticatedUser(currentUser)) {
            buttonsPanel.add(new FollowButton(controller.isFollowing(currentUser, controller.getAuthenticatedUser()), currentUser, controller.getAuthenticatedUser()));
            buttonsPanel.add(Box.createHorizontalStrut(10)); // Add a 10-pixel gap
        }
        else{
            buttonsPanel.add(createEditProfileButton(), BorderLayout.WEST);
            buttonsPanel.add(Box.createHorizontalStrut(10)); // Add a 10-pixel gap
            buttonsPanel.add(createLogoutButton(), BorderLayout.EAST);
        }
        return buttonsPanel;
    }

    /**
     * Creates the profile image.
     * @return The profile image.
     */
    private JPanel createProfileImage() {
        return new AvatarImagePanel(currentUser.getProfilePicturePath(), 80, 80);
    }

    /**
     * Creates the stats panel.
     * @return The stats panel.
     */
    private JPanel createStatsPanel() {
        return new StatsPanel();
    }

    /**
     * Creates the "Edit Profile" button.
     * @return The "Edit Profile" button.
     */
    private JButton createEditProfileButton() {
        ButtonComponent editProfileButton = new ButtonComponent("Edit Profile", 12, 5, Component.CENTER_ALIGNMENT, "secondary", false);
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
    private JButton createLogoutButton() {
        ButtonComponent buttonComponent = new ButtonComponent("Logout", 12, 5, Component.RIGHT_ALIGNMENT, "danger",  false);
        buttonComponent.addActionListener(
                e -> controller.logout()
        );
        return buttonComponent;
    }
}
