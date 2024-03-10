package src.domain.aggregate;

import java.time.LocalDateTime;
import src.domain.entities.User;

/**
 * Aggregate record that represents a follow on Quackstagram.
 *
 * @param user
 * @param follower
 * @param datetime
 */
public record Follow(User user, User follower, LocalDateTime datetime) {

    /**
     * Get the user
     *
     * @return The user that is following
     */
    @Override
    public User user() {
        return this.user;
    }

    /**
     * Get the follower
     *
     * @return The user that is being followed
     */
    @Override
    public User follower() {
        return this.follower;
    }

    /**
     * Constructor for Follower
     *
     * @return The date and time the follow was made
     */
    @Override
    public LocalDateTime datetime() {
        return datetime;
    }

    /**
     * Returns a string representation of the follow
     *
     * @return The string representation of the follow
     */
    @Override
    public String toString() {
        return user.getUsername() + ":" + follower.getUsername() + ":" + datetime.toString();
    }
}
