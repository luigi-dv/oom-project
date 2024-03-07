package src.domain.repositiories;

import src.domain.entities.User;

/**
 * Abstract repository class defining methods for managing user data in the domain.
 */
public interface IUserRepository {

    /**
     * Finds a user by their username.
     *
     * @param username The username of the user to be found.
     * @return The user with the specified username or null if not found.
     * @implNote This method is a placeholder for the actual implementation.
     */
     User findByUsername(String username);

    /**
     * Creates a new user with the provided username and password.
     *
     * @param user The new User.
     * @return The created user entity.
     * @implNote This method is a placeholder for the actual implementation.
     */
     User save(User user);

    /**
     * Updates an existing user with the provided data.
     *
     * @param user The user entity with updated information.
     * @implNote This method is a placeholder for the actual implementation.
     */
     User update(User user);

    /**
     * Deletes a user by their username.
     *
     * @param user The username of the user to be deleted.
     * @implNote This method is a placeholder for the actual implementation.
     */
     User delete(User user);
}
