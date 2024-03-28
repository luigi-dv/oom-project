package src.infrastructure.repositories;

import java.io.File;
import src.domain.entities.Picture;
import src.infrastructure.utilities.storage.PictureStorage;

/**
 * Repository class for the storage of files
 */
public class StorageRepository {

    /**
     * Method to save the user's profile picture to the profile photo storage path
     *
     * @param file The file to save
     * @param username The username
     */
    public boolean saveProfilePicture(File file, String username) {
        return PictureStorage.saveProfilePicture(file, username);
    }

    /**
     * Method to save a picture to the picture storage path
     *
     * @param file The file to save
     * @param picture The username
     */
    public void savePicture(File file, Picture picture) {
        PictureStorage.savePicture(file, picture);
    }
}
