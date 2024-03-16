package src.infrastructure.utilities.file.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;

import src.domain.entities.notifications.Notification;
import src.infrastructure.utilities.file.IFile;

public class NotificationWriter implements IFile {

    protected static final String FILE_PATH = FILE_PATH_ROOT + "notifications.txt";

    public static void writeToFile(Notification notification) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(notification.toString());
            writer.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
