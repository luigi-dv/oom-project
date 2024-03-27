package src.infrastructure.utilities.file.writer;

import java.io.*;
import java.util.UUID;
import src.domain.entities.messages.Message;
import static src.infrastructure.utilities.file.IFile.FILE_PATH_ROOT;

public class MessageWriter {
    protected static final String FILE_PATH = FILE_PATH_ROOT + "messages.json";

    public static Message writeToFile(Message message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(message.toString());
            writer.newLine();
            return message;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void deleteFromFile(Message message) {
        UUID id = message.getId();
        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            IWriter.deleteById(id, reader);
        } catch (FileNotFoundException e) {
            // TODO: Catch exception
            e.printStackTrace();
        } catch (Exception e) {
            // TODO: Catch exception
            e.printStackTrace();
        }
    }
}
