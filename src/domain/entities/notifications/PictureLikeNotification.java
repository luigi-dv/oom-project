package src.domain.entities.notifications;

import src.domain.entities.User;

public class PictureLikeNotification extends Notification {
    /**
     * Constructs a new PictureLikeNotification object.
     *
     * @param notifierUser The user who initiated the like action.
     * @param notifiedUser The user who was notified of the like.
     * @param message The message to display in the notification.
     */
    public PictureLikeNotification(User notifierUser, User notifiedUser, String message) {
        super(NotificationType.PICTURE_LIKE, message, notifierUser, notifiedUser);
    }
}
