package src.domain.entities;

import java.util.UUID;

/**
 * Represents a "Like" entity in the domain, indicating a user's liking of a specific post.
 */
public class Like {

    /**
     * Unique identifier for the like.
     */
    private final UUID id;

    /**
     * The user who created the like.
     */
    private final User user;

    /**
     * The post that has been liked.
     */
    private final Post post;

    /**
     * Constructs a new Like instance.
     *
     * @param user The user who created the like.
     * @param post The post that has been liked.
     */
    public Like(User user, Post post) {
        this.id = UUID.randomUUID();
        this.user = user;
        this.post = post;
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
     * Gets the post that has been liked.
     *
     * @return The post that has been liked.
     */
    public Post getPost() {
        return this.post;
    }
}
