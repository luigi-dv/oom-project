package src.domain.entities.notifications;

import src.domain.entities.User;

public class PictureLikeNotification extends Notification{ 
        
        public PictureLikeNotification(User user, String message) {
            super(NotificationType.PICTURE_LIKE, message, user);
        }
    
}
