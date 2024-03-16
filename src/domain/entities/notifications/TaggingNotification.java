package src.domain.entities.notifications;

import src.domain.entities.User;

public class TaggingNotification extends Notification {

    public TaggingNotification(User user, String message) {
        super(NotificationType.TAGGING, message, user);
    }
    
}
