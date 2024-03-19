package src.domain.entities.notifications;

import java.time.LocalDateTime;

import src.domain.entities.User;

public abstract class Notification {

    private NotificationType type;
    private final User notifiedUser;

    private final User notifierUser;
    private String message;
    private LocalDateTime date;
    private User user;

    public Notification(NotificationType type, String message, User notifierUser, User notifiedUser) {
        this.type = type;
        this.message = message;
        this.date = LocalDateTime.now();
        this.notifierUser = notifierUser;
        this.notifiedUser = notifiedUser;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getType() {
        return NotificationType.toString(type);
    }

    /**
     * Gets the user that is the subject of the notification.
     * @return The user that is the subject of the notification
     */
    public User getNotifierUser() {
        return notifierUser;
    }

    /**
     * Gets the user that is the subject of the notification.
     * @return The user that is the subject of the notification
     */
    public User getNotifiedUser() {
        return notifiedUser;
    }

    public LocalDateTime getTimestamp() {
        return date;
    }

    @Override
    public String toString() {
        return getType() + ":" + message + ":" + date + ":" + user.getUsername();
    }
    
}
