package src.domain.entities.notifications;

public enum NotificationType {

    COMMENT_LIKE,
    TAGGING,
    FOLLOW,
    PICTURE_LIKE;

    public static NotificationType fromString(String type) {
        switch (type) {
            case "COMMENT_LIKE":
                return COMMENT_LIKE;
            case "TAGGING":
                return TAGGING;
            case "FOLLOW":
                return FOLLOW;
            case "PICTURE_LIKE":
                return PICTURE_LIKE;
            default:
                return null;
        }
    }

    public static String toString(NotificationType type) {
        switch (type) {
            case COMMENT_LIKE:
                return "COMMENT_LIKE";
            case TAGGING:
                return "TAGGING";
            case FOLLOW:
                return "FOLLOW";
            case PICTURE_LIKE:
                return "PICTURE_LIKE";
            default:
                return null;
        }
    }
    
}
