package src.infrastructure.repositories;

import java.util.List;
import java.util.UUID;
import src.domain.entities.Picture;
import src.domain.entities.User;
import src.domain.repositiories.IPictureRepository;
import src.infrastructure.utilities.filereaders.PictureReader;
import src.infrastructure.utilities.filewriter.PictureWriter;


/**
 * Repository for managing picture entities.
 */
public class PictureRepository implements IPictureRepository {

    /**
     * Finds a picture by its unique identifier.
     *
     * @param id The unique identifier of the picture.
     * @return The picture with the specified ID or null if not found.
     * @implNote This method is a placeholder for the actual implementation.
     */
    public Picture findById(UUID id) {
        return PictureReader.findById(id);
    }

    /**
     * Finds all pictures associated with a specific user.
     * @param user The username of the user.
     * @return A list of pictures associated with the specified user ID.
     * @implNote This method is a placeholder for the actual implementation.
     */
    public List<Picture> findByUser(User user) {
        return PictureReader.findPicturesFromUser(user);
    }

    /**
     * Creates a new picture.
     *
     * @param picture The picture entity to be created.
     * @return The created picture entity.
     * @implNote This method is a placeholder for the actual implementation.
     */
    public Picture create(Picture picture) {
       return PictureWriter.writeToFile(picture);
    }

    /**
     * Updates an existing picture.
     *
     * @param picture The picture entity with updated information.
     * @return The updated picture entity.
     * @implNote This method is a placeholder for the actual implementation.
     */
    public Picture update(Picture picture) {
        return null;
    }

    /**
     * Deletes a picture by its unique identifier.
     *
     * @param id The unique identifier of the picture to be deleted.
     * @return True if the picture was successfully deleted, false otherwise.
     * @implNote This method is a placeholder for the actual implementation.
     */
    public boolean delete(UUID id) {
        return false;
    }
}
