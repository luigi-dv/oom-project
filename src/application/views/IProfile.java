package src.application.views;

import java.awt.*;
import javax.swing.*;

import src.domain.entities.User;

public interface IProfile {

    int PROFILE_IMAGE_SIZE = 80; // Adjusted size for the profile image to match UI
    int NAV_ICON_SIZE = 20; // Corrected static size for bottom icons

    static JLabel createProfileImage(User user) {
        ImageIcon profileIcon = new ImageIcon(new ImageIcon("resources/storage/images/" + user.getUsername() + ".png")
                .getImage().getScaledInstance(PROFILE_IMAGE_SIZE, PROFILE_IMAGE_SIZE, Image.SCALE_SMOOTH));
        JLabel profileImage = new JLabel(profileIcon);
        profileImage.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return profileImage;
    }

    /**
     * Create a panel containing the user's profile picture.
     *
     * @return JPanel
     */
    static JButton createEditProfileButton() {
        JButton editProfileButton = new JButton("Edit Profile");
        editProfileButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        editProfileButton.setFont(new Font("Arial", Font.BOLD, 12));
        editProfileButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, editProfileButton.getMinimumSize().height)); // Make the
        // button fill the horizontal space
        editProfileButton.setBackground(new Color(225, 228, 232)); // A soft, appealing color that complements the UI
        editProfileButton.setForeground(Color.BLACK);
        editProfileButton.setOpaque(true);
        editProfileButton.setBorderPainted(false);
        editProfileButton.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Add some vertical padding
        return editProfileButton;
    }

    /**
     * Create a panel with the user's stats
     *
     * @return JPanel
     */
    static JPanel createStatsFollowPanel(User user) {
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        statsPanel.setBackground(new Color(249, 249, 249));
        System.out.println("Number of posts for this user" + user.getProfile().getPostsCount());
        statsPanel.add(createStatLabel(Integer.toString(user.getProfile().getPostsCount()), "Posts"));
        statsPanel.add(createStatLabel(Integer.toString(user.getProfile().getFollowersCount()), "Followers"));
        statsPanel.add(createStatLabel(Integer.toString(user.getProfile().getFollowingCount()), "Following"));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(25, 0, 10, 0)); // Add some vertical padding
        return statsPanel;
    }


    /**
     * Create a label for the user's stats
     *
     * @param number The number to display
     * @param text The text to display
     * @return JLabel
     */
    static JLabel createStatLabel(String number, String text) {
        JLabel label = new JLabel("<html><div style='text-align: center;'>" + number + "<br/>" + text + "</div></html>",
                SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setForeground(Color.BLACK);
        return label;
    }

    /**
     * Creates a panel containing the stats.
     *
     * @return JPanel
     */
    static JPanel createStatsPanel() {
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.Y_AXIS));
        return statsPanel;
    }

    /**
     * Creates a panel containing the profile bio.
     *
     * @return JPanel
     */
    static JTextArea createProfileBio(User user) {
        JTextArea profileBio = new JTextArea(user.getBio());
        System.out.println("This is the bio " + user.getUsername());
        profileBio.setEditable(false);
        profileBio.setFont(new Font("Arial", Font.PLAIN, 12));
        profileBio.setBackground(new Color(249, 249, 249));
        profileBio.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        return profileBio;
    }

    /**
     * Creates a panel containing the profile name and bio.
     *
     * @return JPanel
     */
    static JPanel createProfileNameAndBioPanel(User user) {
        JPanel profileNameAndBioPanel = new JPanel();
        profileNameAndBioPanel.setLayout(new BorderLayout());
        profileNameAndBioPanel.setBackground(new Color(249, 249, 249));
        profileNameAndBioPanel.add(IProfile.createProfileNameLabel(user));
        profileNameAndBioPanel.add(IProfile.createProfileBio(user));
        return profileNameAndBioPanel;
    }

    /**
     * Creates a panel containing the profile name label.
     *
     * @return JPanel
     */
    static JLabel createProfileNameLabel(User user) {
        JLabel profileNameLabel = new JLabel(user.getUsername());
        profileNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        profileNameLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        return profileNameLabel;
    }

}
