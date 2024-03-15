package src.domain.entities.notifications;

import java.time.LocalDateTime;

import src.domain.entities.User;

public abstract class Notification {

    private NotificationType type;
    private String message;
    private LocalDateTime date;
    private User user;

    public Notification(NotificationType type, String message, User user) {
        this.type = type;
        this.message = message;
        this.date = LocalDateTime.now();
        this.user = user;
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

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return getType() + ":" + message + ":" + date + ":" + user.getUsername();
    }
    
}
