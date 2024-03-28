package src.presentation.utils;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * A utility class for formatting LocalDateTime objects into human-readable time ago strings.
 */
public class TimeAgoFormatter {
    /**
     * Formats a LocalDateTime into a human-readable time ago string.
     * @param dateTime The LocalDateTime to format.
     * @return A human-readable time ago string.
     */
    public static String format(LocalDateTime dateTime) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(dateTime, now);

        long seconds = duration.getSeconds();

        if (seconds < 60) {
            return "just now";
        } else if (seconds < 3600) {
            long minutes = seconds / 60;
            return minutes + (minutes == 1 ? " minute ago" : " minutes ago");
        } else if (seconds < 86400) {
            long hours = seconds / 3600;
            return hours + (hours == 1 ? " hour ago" : " hours ago");
        } else {
            long days = seconds / 86400;
            return days + (days == 1 ? " day ago" : " days ago");
        }
    }
}
