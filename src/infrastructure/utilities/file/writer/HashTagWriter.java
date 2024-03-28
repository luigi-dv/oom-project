package src.infrastructure.utilities.file.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import src.domain.entities.HashTag;
import src.infrastructure.utilities.file.IFile;

public class HashTagWriter implements IFile {

    private static final String FILE_PATH = FILE_PATH_ROOT + "hashtag.txt";

    public static void writeLineToFile(HashTag hashTag) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(hashTag.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace(); // Handle IOException appropriately
        } catch (Exception e) {
            e.printStackTrace(); // Handle other exceptions appropriately
        }
    }

    
}
