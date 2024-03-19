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
    
    public static Notification createNotification(
            NotificationType type,
            User notifierUser,
            User notifiedUser,
            String message,
            LocalDateTime date
    ) {
        Notification notification;
        switch (type) {
            case COMMENT_LIKE:
                notification = new CommentLikeNotification(notifierUser, notifiedUser, message);
                notification.setDate(date);
                break;
            case TAGGING:
                notification = new TaggingNotification(notifierUser, notifiedUser, message);
                notification.setDate(date);
                break;
            case FOLLOW:
                notification = new FollowNotification(notifierUser, notifiedUser, message);
                notification.setDate(date);
                break;
            case PICTURE_LIKE:
                notification = new PictureLikeNotification(notifierUser, notifiedUser, message);
                notification.setDate(date);
                break;
            default:
                return null;
        }
        return notification;
    }
    
    
}
