package src.domain.entities.notifications;

import src.domain.entities.User;

public class CommentLikeNotification extends Notification {
    
    public CommentLikeNotification(User user, String message) {
        super(NotificationType.COMMENT_LIKE, message, user);
    }
    
}
