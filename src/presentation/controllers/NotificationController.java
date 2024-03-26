package src.presentation.controllers;

import src.application.services.NotificationService;
import src.domain.entities.User;
import src.domain.entities.notifications.Notification;

import java.util.List;

public class NotificationController extends BaseController {

    /**
     * The service responsible for handling notifications.
     */
    private final NotificationService service;

    /**
     * Constructs an instance of NotificationController, initializing the NotificationService.
     */
    public NotificationController() {
        this.service = new NotificationService();
    }

    /**
     * Gets the notifications for a user.
     *
     * @return The notifications for the user
     */
    public List<Notification> getNotifications(User user) {
        return service.getNotificationsFromUser(user);
    }
}
