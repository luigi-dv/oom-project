package src.application.services;

import java.util.List;

import src.domain.entities.User;
import src.domain.entities.notifications.Notification;
import src.infrastructure.repositories.NotificationRepository;


public class NotificationService {

    private final NotificationRepository repository;

    public NotificationService() {
        this.repository = new NotificationRepository();
    }

    public List<Notification> getNotificationsFromUser(User user) {
        return repository.getNotificationsFromUser(user);
    }

    public Notification writeNotification(Notification notification) {
        return repository.writeNotification(notification);
    }
    
}
