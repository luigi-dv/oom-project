package src.infrastructure.repositories;

import java.util.List;

import src.domain.entities.User;
import src.domain.entities.notifications.Notification;
import src.domain.repositiories.INotificationRepository;
import src.infrastructure.utilities.file.reader.NotificationReader;
import src.infrastructure.utilities.file.writer.NotificationWriter;

public class NotificationRepository implements INotificationRepository {

    public List<Notification> getNotificationsFromUser(User user) {
        return NotificationReader.readNotifications(user);
    }

    public Notification writeNotification(Notification notification) {
        NotificationWriter.writeToFile(notification);
        return notification;
    }
    
}
