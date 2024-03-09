package src.infrastructure.repositories;

import java.util.List;
import java.util.UUID;

import src.domain.interfaces.ILikeable;
import src.domain.repositiories.ILikeRepository;
import src.infrastructure.utilities.file.writer.LikeWriter;

/**
 * Repository class responsible for handling like data storage and retrieval in the infrastructure layer.
 */
public class LikeRepository<T extends ILikeable> implements ILikeRepository<T> {

    /**
     * Finds a like by its unique identifier.
     *
     * @param id The unique identifier of the like.
     * @return The like with the specified ID or null if not found.
     */
    public src.domain.entities.Like<T> findById(UUID id) {
        return null;
    }

    /**
     * Saves a new like.
     *
     * @param like The like entity to be saved.
     * @return The saved like entity.
     */
    public src.domain.entities.Like<T> save(src.domain.entities.Like<T> like) {
        LikeWriter<T> likeWriter = new LikeWriter<>();
        return likeWriter.writeToFile(like);
    }

    /**
     * Deletes a like.
     *
     * @param uuid The like UUID to be deleted.
     * @return The deleted like entity.
     */
    public src.domain.entities.Like<T> delete(UUID uuid) {
        // TODO: Implement search
        src.domain.entities.Like<T> e = this.findById(uuid);
        // Todo Return the object
        return null;
    }

    /**
     * Finds all likes associated with a specific post.
     *
     * @param postId The unique identifier of the post.
     * @return A list of likes associated with the specified post ID.
     */
    public List<src.domain.entities.Like<T>> findByPostId(UUID postId) {
        return null;
    }
}
