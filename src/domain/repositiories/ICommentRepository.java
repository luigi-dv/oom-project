package src.domain.repositiories;

import src.domain.entities.Comment;

import java.util.List;
import java.util.UUID;

/**
 * The interface defining operations for managing comments in a repository.
 */
public interface ICommentRepository {
    /**
     * Finds comments by post ID.
     *
     * @param id The ID of the post.
     * @return A list of comments associated with the specified post ID.
     */
    List<Comment> findByPostId(UUID id);

    /**
     * Creates a new comment.
     *
     * @param comment The comment to create.
     * @return The created comment.
     */
    Comment create(Comment comment);

    /**
     * Updates an existing comment.
     *
     * @param comment The comment to update.
     * @return The updated comment.
     */
    Comment update(Comment comment);

    /**
     * Deletes a comment by its ID.
     *
     * @param id The ID of the comment to delete.
     * @return True if the comment was successfully deleted, false otherwise.
     */
    boolean delete(UUID id);
}
