package src.application.views;

import src.application.controllers.UIController;
import src.application.views.interfaces.UIConstants;
import src.domain.entities.User;
import src.domain.entities.notifications.Notification;



import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class NotificationsUI extends JPanel {

    private final GUI gui;
    private final UIController controller;

    public NotificationsUI(GUI gui, User user) {
        this.gui = gui;
        this.controller = new UIController();
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

        List<Notification> notifications = controller.getNotifications(gui.currentUser);

        for (Notification notification : notifications) {

            System.out.println(notification);
            JPanel notificationPanel = new JPanel(new BorderLayout());
            notificationPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

            JLabel notificationLabel = new JLabel(notification.getMessage() + " - " + getElapsedTime(notification.getTimestamp()) + " ago");
            notificationPanel.add(notificationLabel, BorderLayout.CENTER);

            contentPanel.add(notificationPanel);
        }

        add(scrollPane);
    }

    private String getElapsedTime(LocalDateTime timestamp) {

        LocalDateTime currentTime = LocalDateTime.now();

        long daysBetween = ChronoUnit.DAYS.between(timestamp, currentTime);
        long minutesBetween = ChronoUnit.MINUTES.between(timestamp, currentTime) % 60;

        StringBuilder timeElapsed = buildTimeElapsedString(daysBetween, minutesBetween);
        return timeElapsed.toString();
    }

    private StringBuilder buildTimeElapsedString(long daysBetween, long minutesBetween) {
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
        return timeElapsed;
    }
}

