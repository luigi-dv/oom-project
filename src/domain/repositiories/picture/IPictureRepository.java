package src.domain.repositiories.picture;

import src.domain.entities.Picture;

/**
 * Interface defining operations for managing picture entities in the domain.
 */
public interface IPictureRepository {

    /**
     * Finds a picture by its unique identifier.
     *
     * @param id The unique identifier of the picture.
     * @return The picture with the specified ID or null if not found.
     */
    Picture findById(int id);

    /**
     * Creates a new picture.
     *
     * @param picture The picture entity to be created.
     * @return The created picture entity.
     */
    Picture create(Picture picture);

    /**
     * Updates an existing picture.
     *
     * @param picture The picture entity with updated information.
     * @return The updated picture entity.
     */
    Picture update(Picture picture);

    /**
     * Deletes a picture by its unique identifier.
     *
     * @param id The unique identifier of the picture to be deleted.
     * @return True if the picture was successfully deleted, false otherwise.
     */
    boolean delete(int id);
}
