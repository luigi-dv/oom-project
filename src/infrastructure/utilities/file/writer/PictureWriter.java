package src.infrastructure.utilities.file.writer;

import src.infrastructure.utilities.file.IFile;

import java.io.*;
import java.util.UUID;

import src.domain.entities.Picture;
/**
 * The following utility class implements the Picture Writing methods for store data
 */
public class PictureWriter implements IFile {
    protected static final String FILE_PATH = FILE_PATH_ROOT + "pictures.txt";

    /**
     * Writes the picture to the pictures file.
     *
     * @param picture The picture to write to the file.
     * @return The picture that was written to the file.
     */
    public static Picture writeToFile(Picture picture) {
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

    public static void deleteFromFile(Picture picture) {
        UUID id = picture.getId();
        StringBuilder out = new StringBuilder();

        try(BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            IWriter.deleteById(id, reader);
        } catch (FileNotFoundException e) {
            // TODO: Catch exception
            e.printStackTrace();
        } catch (Exception e) {
            // TODO: Catch exception
            e.printStackTrace();
        }
        rewriteFile(out.toString());
    }

    private static void rewriteFile(String updatedContent) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(updatedContent);
        }  catch (FileNotFoundException e) {
            // TODO: Catch exception
            e.printStackTrace();
        } catch (Exception e) {
            // TODO: Catch exception
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        UUID id = UUID.fromString("bab06116-543c-4bb2-9861-b71850201ed2");
        Picture picture = new Picture(id);
        deleteFromFile(picture);
    }

}
