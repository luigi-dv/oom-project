package src.presentation.components.notifications;

import src.domain.entities.notifications.Notification;



import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import src.presentation.components.ui.AvatarImagePanel;


public class NotificationComponent extends JPanel {

    private final Notification notification;

    /**
     * Creates a panel to display a notification.
     * @param notification the notification to display
     */
    public NotificationComponent(Notification notification) {
        this.notification = notification;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create and configure components to display notification information
        String labelText = "<html><b>" + notification.getNotifierUser().getUsername() + "</b> " + notification.getMessage() + "</html>";
        JLabel notificationLabel = new JLabel(labelText);
        notificationLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        // Time elapsed
        JLabel dateLabel = new JLabel(getElapsedTime(notification.getDate()));
        dateLabel.setFont(new Font("Arial", Font.BOLD, 14));

        // User image
        AvatarImagePanel userImageIcon = new AvatarImagePanel(notification.getNotifierUser().getProfilePicturePath(), 50,50);
        JLabel userImageLabel = new JLabel();

        // Add components to the panel
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.X_AXIS));
        textPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Add gap before image
        textPanel.add(userImageIcon);
        textPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Add gap after image
        textPanel.add(notificationLabel);
        textPanel.add(Box.createRigidArea(new Dimension(5, 0))); // Reduce gap before date
        textPanel.add(dateLabel);

        add(textPanel, BorderLayout.CENTER);

        // Adjust the preferred size to fit the content
        setPreferredSize(new Dimension(400, textPanel.getPreferredSize().height + 20)); // Add 20 for top and bottom padding
    }

    private String getElapsedTime(LocalDateTime timestamp) {

        LocalDateTime currentTime = LocalDateTime.now();

        long secondsBetween = ChronoUnit.SECONDS.between(timestamp, currentTime);
        long minutesBetween = ChronoUnit.MINUTES.between(timestamp, currentTime);
        long hoursBetween = ChronoUnit.HOURS.between(timestamp, currentTime);
        long daysBetween = ChronoUnit.DAYS.between(timestamp, currentTime);
        long weeksBetween = ChronoUnit.WEEKS.between(timestamp, currentTime);

        if (weeksBetween > 0) {
            return weeksBetween + "w";
        } else if (daysBetween > 0) {
            return daysBetween + "d";
        } else if (hoursBetween > 0) {
            return hoursBetween + "h";
        } else if (minutesBetween > 0) {
            return minutesBetween + "m";
        } else {
            return secondsBetween + "s";
        }
    }
}
