package src.infrastructure.utilities.storage;

import src.domain.entities.Picture;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * Utility class responsible for storing pictures.
 * This class provides methods for saving profile pictures and other pictures associated with entities.
 */
public class PictureStorage {

    /**
     * The storage path for profile photos.
     */
    private static final String PROFILE_PHOTO_STORAGE_PATH = "resources/storage/images/";

    /**
     * Save the user's profile picture to the profile photo storage path.
     *
     * @param file     The file representing the image to save.
     * @param username The username associated with the profile picture.
     */
    public static void saveProfilePicture(File file, String username) {
        try {
            BufferedImage image = ImageIO.read(file);
            File outputFile = new File(PROFILE_PHOTO_STORAGE_PATH + username + ".png");
            ImageIO.write(image, "png", outputFile);
        } catch (IOException e) {
            handleImageIOException(e);
        }
    }

    /**
     * Save a picture associated with an entity (e.g., User, Product) to the storage path.
     *
     * @param file    The file representing the image to save.
     * @param picture The Picture entity containing information about the picture.
     */
    public static void savePicture(File file, Picture picture) {
        try {
            BufferedImage image = ImageIO.read(file);
            File outputFile = new File(PROFILE_PHOTO_STORAGE_PATH + picture.getId() + ".png");
            ImageIO.write(image, "png", outputFile);
        } catch (IOException e) {
            handleImageIOException(e);
        }
    }

    /**
     * Handles IOException during image processing by printing the stack trace.
     *
     * @param e The IOException thrown during image processing.
     */
    private static void handleImageIOException(IOException e) {
        e.printStackTrace();
        // You may choose to log the exception or handle it differently based on your application's requirements.
    }
}
