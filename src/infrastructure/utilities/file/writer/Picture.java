package src.infrastructure.utilities.file.writer;

import src.infrastructure.utilities.file.IFile;

import java.io.FileWriter;
import java.io.BufferedWriter;

/**
 * The following utility class implements the Picture Writing methods for store data
 */
public class Picture implements IFile {
    protected static final String FILE_PATH = FILE_PATH_ROOT + "pictures.txt";

    /**
     * Writes the picture to the pictures file.
     *
     * @param picture The picture to write to the file.
     * @return The picture that was written to the file.
     */
    public static src.domain.entities.Picture writeToFile(src.domain.entities.Picture picture) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
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
