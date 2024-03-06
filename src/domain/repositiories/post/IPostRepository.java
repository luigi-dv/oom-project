package src.domain.repositiories.post;


import java.util.List;
import java.util.UUID;
import src.domain.entities.User;
import src.domain.aggregates.Post;

/**
 * Interface defining operations for managing post entities in the domain.
 */
public interface IPostRepository {

    /**
     * Finds a post by its unique identifier.
     *
     * @param postId The unique identifier of the post.
     * @return The post with the specified ID or null if not found.
     */
    Post findById(UUID postId);

    /**
     * Saves a new post.
     *
     * @param post The post entity to be saved.
     * @return The saved post entity.
     */
    Post save(Post post);

    /**
     * Updates an existing post.
     *
     * @param post The post entity with updated information.
     * @return The updated post entity.
     */
    Post update(Post post);

    /**
     * Deletes a post.
     *
     * @param post The post entity to be deleted.
     * @return The deleted post entity.
     */
    Post delete(Post post);

    /**
     * Retrieves all posts from the users that the current user follows.
     *
     * @return A list of all posts in the system.
     */
    List<Post> getFollowingPosts(User user);

    /**
     * Retrieves all posts from a specific user.
     *
     * @param userId The user ID to search for.
     * @return A list of all posts from the specified user.
     */
    List<Post> findByUserId(UUID userId);
}

