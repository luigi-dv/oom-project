package src.application.services.user;

import src.domain.entities.User;
import src.infrastructure.repositories.user.UserRepository;

/**
 * Service class responsible for managing user-related operations in the application.
 */
public class UserService {

    /**
     * The repository for handling user data.
     */
    private final UserRepository userRepository;

    /**
     * Constructs a UserService instance, initializing the UserRepository.
     */
    public UserService() {
        this.userRepository = new UserRepository();
    }

    /**
     * Retrieves a user by their unique identifier.
     *
     * @param id The unique identifier of the user.
     * @return The user with the specified ID or null if not found.
     */
    public User getUserById(int id) {
        return this.userRepository.findById(id);
    }

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user to be retrieved.
     * @return The user with the specified username or null if not found.
     */
    public User getUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    /**
     * Creates a new user.
     *
     * @param user The user to be created.
     * @return The created user entity.
     */
    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    /**
     * Updates an existing user.
     *
     * @param user The user with updated information.
     * @return The updated user entity.
     */
    public User updateUser(User user) {
        return this.userRepository.update(user);
    }

    /**
     * Deletes a user by their username.
     *
     * @param username The username of the user to be deleted.
     * @return True if the user was successfully deleted, false otherwise.
     */
    public boolean deleteUser(String username) {
        return this.userRepository.delete(username);
    }
}
