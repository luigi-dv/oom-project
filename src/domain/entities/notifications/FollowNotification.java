package src.domain.entities.notifications;

import src.domain.entities.User;

public class FollowNotification extends Notification {

    public FollowNotification(User user, String message) {
        super(NotificationType.FOLLOW, message, user);
    }
    
}
