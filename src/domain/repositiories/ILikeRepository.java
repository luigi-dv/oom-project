package src.domain.repositiories;

import java.util.List;
import java.util.UUID;

import src.domain.interfaces.ILikeable;
import src.domain.entities.Like;

/**
 * Interface defining operations for managing like entities in the domain.
 */
public interface ILikeRepository<T extends ILikeable> {

    /**
     * Finds a like by its unique identifier.
     *
     * @param id The unique identifier of the like.
     * @return The like with the specified ID or null if not found.
     */
    Like<T> findById(UUID id);

    /**
     * Saves a new like.
     *
     * @param like The like entity to be saved.
     * @return The saved like entity.
     */
    Like<T> save(Like<T> like);

    /**
     * Deletes a like.
     *
     * @param id The like UUID to be deleted.
     * @return The deleted like entity.
     */
    Like<T> delete(UUID id);

    /**
     * Finds all likes associated with a specific post.
     *
     * @param postId The unique identifier of the post.
     * @return A list of likes associated with the specified post ID.
     */
    List<Like<T>> findByPostId(UUID postId);
}
