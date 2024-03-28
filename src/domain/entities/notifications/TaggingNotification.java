package src.domain.entities.notifications;

import src.domain.entities.User;

public class TaggingNotification extends Notification {

    /**
     * Constructs a new TaggingNotification object.
     *
     * @param notifierUser The user who initiated the tagging action.
     * @param notifiedUser The user who was tagged.
     * @param message The message to display in the notification.
     */
    public TaggingNotification(User notifierUser, User notifiedUser, String message) {
        super(NotificationType.TAGGING, message, notifierUser, notifiedUser);
    }
    
}
