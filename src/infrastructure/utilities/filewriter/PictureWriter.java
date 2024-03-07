package src.infrastructure.utilities.filewriter;

import src.domain.entities.Picture;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * The following utility class implements the Picture Writing methods for store data
 */
public class PictureWriter {
    protected static final String credentialsFilePath = "src/infrastructure/persistance/data/pictures.txt";
    public static Picture writeToFile(Picture picture) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(credentialsFilePath, true))) {
            writer.write(picture.toString());
            writer.newLine();
            return picture;
        } catch (Exception e) {
            // TODO: Catch exception
            e.printStackTrace();
            return null;
        }
    }

}
