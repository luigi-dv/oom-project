package src.domain.entities.notifications;

import src.domain.entities.User;

public class FollowNotification extends Notification {

    /**
     * Constructs a new FollowNotification object.
     * 
     * @param notifierUser The user who initiated the follow action.
     * @param notifiedUser The user who was followed.
     * @param message The message to display in the notification.
     */
    public FollowNotification(User notifierUser, User notifiedUser, String message) {
        super(NotificationType.FOLLOW, message, notifierUser, notifiedUser);
    }
    
}
