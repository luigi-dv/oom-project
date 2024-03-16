package src.infrastructure.utilities.file.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import src.infrastructure.utilities.file.IFile;
import src.domain.entities.User;
import src.domain.entities.notifications.Notification;
import src.domain.entities.notifications.NotificationType;
import src.domain.factorybuilder.NotificationFactory;

public class NotificationReader implements IFile {

    private static final String FILE_PATH = FILE_PATH_ROOT + "notifications.txt";

    public static boolean doesFileExist() {
        return Files.exists(Path.of(FILE_PATH));
    }

    public static List<Notification> readNotifications(User user) {
        List<Notification> notifications = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Notification notification = parseNotificationFromLine(line, user);
                if (notification != null) {
                    notifications.add(notification);
                }
            }
        } catch (IOException e) {
            handleIOException(e);
        } catch (Exception e) {
            handleOtherException(e);
        }

        return notifications;
    }

    private static Notification parseNotificationFromLine(String line, User user) {
        String[] parts = line.split(";");

        String typeString = parts[0];
        String message = parts[1];
        String date = parts[2];
        String username = parts[3];

        if (!username.equals(user.getUsername())) {
            return null;
        }

        LocalDateTime dateTime = LocalDateTime.parse(date);
        NotificationType type = NotificationType.fromString(typeString);
        return NotificationFactory.createNotification(type, message,  new User(username), dateTime);
    }

    private static void handleIOException(IOException e) {
        e.printStackTrace();
    }

    private static void handleOtherException(Exception e) {
        e.printStackTrace();
    }
}
