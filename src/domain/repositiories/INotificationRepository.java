package src.domain.repositiories;

import java.util.List;

import src.domain.entities.notifications.Notification;
import src.domain.entities.User;

public interface INotificationRepository {

    List<Notification> getNotificationsFromUser(User user);
    Notification writeNotification(Notification notification);
    
    
}
