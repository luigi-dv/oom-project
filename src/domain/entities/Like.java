package src.domain.entities;

import src.domain.interfaces.ILikeable;

import java.util.UUID;

/**
 * Represents a "Like" entity in the domain, indicating a user's liking of a specific post.
 *
 * @param <T> Type of the content that is liked (comment or picture).
 */
public class Like<T extends ILikeable> {

    /**
     * Unique identifier for the like.
     */
    private final UUID id;

    /**
     * The user who created the like.
     */
    private final User user;

    /**
     * The content that has been liked (comment or picture).
     */
    private final T content;

    /**
     * Constructs a new Like instance.
     *
     * @param user    The user who created the like.
     * @param content The content that has been liked (comment or picture).
     */
    public Like(User user, T content) {
        this.id = UUID.randomUUID();
        this.user = user;
        this.content = content;
    }

    /**
     * Constructs a new Like instance with a specific unique identifier.
     * @param id The unique identifier of the like.
     * @param user The user who created the like.
     * @param content The content that has been liked (comment or picture).
     */
    public Like(UUID id, User user, T content) {
        this.id = id;
        this.user = user;
        this.content = content;
    }

    /**
     * Factory interface for creating specific types of content.
     *
     * @param <T> Type of the content.
     */
    public interface ILikeableFactory<T extends ILikeable> {
        T create(UUID id);
    }

    /**
     * Factory for creating Like instances for Comment content.
     */
    public static class CommentLikeFactory implements ILikeableFactory<Comment> {
        @Override
        public Comment create(UUID id) {
            return new Comment(id);
        }
    }

    /**
     * Factory for creating Like instances for Picture content.
     */
    public static class PictureLikeFactory implements ILikeableFactory<Picture> {
        @Override
        public Picture create(UUID id) {
            return new Picture(id);
        }
    }


    /**
     * Gets the unique identifier of the like.
     *
     * @return The unique identifier of the like.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Gets the user who created the like.
     *
     * @return The user who created the like.
     */
    public User getUser() {
        return this.user;
    }

    /**
     * Gets the content that has been liked (comment or picture).
     *
     * @return The content that has been liked (comment or picture).
     */
    public T getContent() {
        return this.content;
    }

    @Override
    public String toString() {
        return content.getType() + ":" + id.toString() + ":" + user.getUsername() + ":" + content.getId();
    }
}
