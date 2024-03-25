package src.infrastructure.utilities.file.writer;

import src.domain.entities.messages.Message;


import java.io.BufferedWriter;
import java.io.FileWriter;

import static src.infrastructure.utilities.file.IFile.FILE_PATH_ROOT;

public class MessageWriter {
    protected static final String FILE_PATH = FILE_PATH_ROOT + "messages.txt";

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
}
