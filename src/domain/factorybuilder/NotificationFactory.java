package src.domain.factorybuilder;

import java.time.LocalDateTime;

import src.domain.entities.User;
import src.domain.entities.notifications.CommentLikeNotification;
import src.domain.entities.notifications.FollowNotification;
import src.domain.entities.notifications.Notification;
import src.domain.entities.notifications.NotificationType;
import src.domain.entities.notifications.PictureLikeNotification;
import src.domain.entities.notifications.TaggingNotification;

public class NotificationFactory {
    
    public static Notification createNotification(NotificationType type, String message, User user, LocalDateTime date) {
        Notification notification;
        switch (type) {
            case COMMENT_LIKE:
                notification = new CommentLikeNotification(user, message);
                notification.setDate(date);
                break;
            case TAGGING:
                notification = new TaggingNotification(user, message);
                notification.setDate(date);
                break;
            case FOLLOW:
                notification = new FollowNotification(user, message);
                notification.setDate(date);
                break;
            case PICTURE_LIKE:
                notification = new PictureLikeNotification(user, message);
                notification.setDate(date);
                break;
            default:
                return null;
        }
        return notification;
    }
    
    
}
