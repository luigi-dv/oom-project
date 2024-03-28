package src.presentation.components.profile;

import src.domain.entities.User;
import src.presentation.controllers.profile.StatsPanelController;

import javax.swing.*;
import java.awt.*;

public class StatsPanel extends JPanel {

    private final StatsPanelController controller;
    public StatsPanel() {
        this.controller = new StatsPanelController();
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(Color.WHITE);
        add(createStatsFollowPanel());
    }

    private JPanel createStatsFollowPanel() {
        User currentUser = controller.getAuthenticatedUser();
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        statsPanel.add(createStatLabel(Integer.toString(currentUser.getProfile().getPostsCount()), "post"));
        statsPanel.add(createStatLabel(Integer.toString(currentUser.getProfile().getFollowersCount()), "followers"));
        statsPanel.add(createStatLabel(Integer.toString(currentUser.getProfile().getFollowingCount()), "following"));
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
    private JLabel createStatLabel(String number, String text) {
        JLabel label = new JLabel("<html><div style='text-align: center;'>" + number + "<br/>" + text + "</div></html>",
                SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setForeground(Color.BLACK);
        return label;
    }
}
