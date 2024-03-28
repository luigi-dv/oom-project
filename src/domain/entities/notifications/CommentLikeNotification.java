package src.domain.entities.notifications;

import src.domain.entities.User;

public class CommentLikeNotification extends Notification {

    /**
     * Constructs a new CommentLikeNotification object.
     *
     * @param notifierUser The user who initiated the like action.
     * @param notifiedUser The user who was notified of the like.
     * @param message The message to display in the notification.
     */
    public CommentLikeNotification(User notifierUser, User notifiedUser, String message) {
        super(NotificationType.COMMENT_LIKE, message, notifierUser, notifiedUser);
    }
    
}
