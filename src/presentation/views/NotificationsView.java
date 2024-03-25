package src.presentation.views;

import src.presentation.Router;
import src.presentation.components.notifications.NotificationComponent;
import src.presentation.controllers.NotificationController;
import src.presentation.interfaces.UIConstants;
import src.domain.entities.notifications.Notification;



import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

public class NotificationsView extends JPanel {
    private final Router router;
    private final NotificationController controller;

    public NotificationsView(Router router) {
        this.router = router;
        this.controller = new NotificationController();
        setSize(UIConstants.WIDTH, UIConstants.HEIGHT);
        setMinimumSize(new Dimension(UIConstants.WIDTH, UIConstants.HEIGHT));
        setLayout(new BorderLayout());
        initializeUI();
    }

    private void initializeUI() {
        // Content Panel for notifications
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        List<Notification> notifications = controller.getNotifications(controller.getAuthenticatedUser());

        for (Notification notification : notifications) {
            NotificationComponent notificationComponent = new NotificationComponent(notification);
            contentPanel.add(notificationComponent);
        }

        // Create a JScrollPane
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        // Add the scroll pane to this panel
        add(scrollPane, BorderLayout.CENTER);
    }
}

