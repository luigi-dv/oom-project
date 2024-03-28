package src.domain.repositiories;

import java.util.List;

import src.domain.entities.notifications.Notification;
import src.domain.entities.User;

/**
 * The interface defining operations for managing notifications in a repository.
 */
public interface INotificationRepository {
    /**
     * Retrieves notifications for a specific user.
     *
     * @param user The user for whom to retrieve notifications.
     * @return A list of notifications associated with the specified user.
     */
    List<Notification> getNotificationsFromUser(User user);

    /**
     * Writes a new notification.
     *
     * @param notification The notification to write.
     * @return The written notification.
     */
    Notification writeNotification(Notification notification);
}

