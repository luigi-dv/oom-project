package src.domain.repositiories;

import src.domain.entities.User;
import src.domain.aggregate.Follow;

import java.util.List;

/**
 * The interface defining operations for managing follows in a repository.
 */
public interface IFollowRepository {
    /**
     * Finds the followers by the username of the user aggregate property.
     *
     * @param user The username of the user aggregate property.
     * @return The list of users following the user.
     */
     List<User> findByUser(User user);

    /**
     * Finds the users that a user is following.
     *
     * @param user The user to get the following users of.
     * @return The list of users that the user is following.q
     */
     List<User> findByFollower(User user);

    /**
     * Creates a new user with the provided username and password.
     *
     * @param follow The follow aggregate to save.
     * @return The created follow aggregate.
     */
    Follow save(Follow follow);

    /**
     * Updates an existing follow with the provided data.
     * @param follow The follow aggregate with updated information.
     * @return The updated aggregate.
     */
    Follow update(Follow follow);

    /**
     * Deletes a follow by their username.
     *
     * @param follow The follow aggregate to be deleted.
     */
    Follow delete(Follow follow);
}
