package src.application.views;

import src.application.views.interfaces.UIConstants;
import src.domain.entities.User;
import src.infrastructure.utilities.Crypter;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class NotificationsUI extends JPanel {

    private final GUI gui;

    public NotificationsUI(GUI gui, User user) {
        this.gui = gui;
        setSize(UIConstants.WIDTH, UIConstants.HEIGHT);
        setMinimumSize(new Dimension(UIConstants.WIDTH, UIConstants.HEIGHT));
        setLayout(new BorderLayout());
        initializeUI();
    }

    private void initializeUI() {
        // Content Panel for notifications
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Read the current username from users.txt
        String currentUsername = gui.currentUser.getUsername();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get("data", "notifications.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (Crypter.encryptedStringToString(parts[0].trim()).equals(currentUsername)) {
                    // Format the notification message
                    String userWhoLiked = Crypter.encryptedStringToString(parts[1].trim());
                    // String imageId = Crypter.encryptedStringToString(parts[2].trim());
                    String timestamp = Crypter.encryptedStringToString(parts[3].trim());
                    String notificationMessage = userWhoLiked + " liked your picture - " + getElapsedTime(timestamp)
                            + " ago";

                    // Add the notification to the panel
                    JPanel notificationPanel = new JPanel(new BorderLayout());
                    notificationPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

                    JLabel notificationLabel = new JLabel(notificationMessage);
                    notificationPanel.add(notificationLabel, BorderLayout.CENTER);

                    // Add profile icon (if available) and timestamp
                    // ... (Additional UI components if needed)

                    contentPanel.add(notificationPanel);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Add panels to frame
        add(scrollPane);
    }

    private String getElapsedTime(String timestamp) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime timeOfNotification = LocalDateTime.parse(timestamp, formatter);
        LocalDateTime currentTime = LocalDateTime.now();

        long daysBetween = ChronoUnit.DAYS.between(timeOfNotification, currentTime);
        long minutesBetween = ChronoUnit.MINUTES.between(timeOfNotification, currentTime) % 60;

        StringBuilder timeElapsed = new StringBuilder();
        if (daysBetween > 0) {
            timeElapsed.append(daysBetween).append(" day").append(daysBetween > 1 ? "s" : "");
        }
        if (minutesBetween > 0) {
            if (daysBetween > 0) {
                timeElapsed.append(" and ");
            }
            timeElapsed.append(minutesBetween).append(" minute").append(minutesBetween > 1 ? "s" : "");
        }
        return timeElapsed.toString();
    }
}

