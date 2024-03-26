package src.application.services;

import java.util.List;

import src.application.providers.SessionProvider;
import src.domain.entities.User;
import src.infrastructure.repositories.UserRepository;

/**
 * Service class responsible for managing user-related operations in the application.
 */
public class UserService {

    /**
     * The repository for handling user data.
     */
    private final UserRepository userRepository;

    /**
     * The session provider for managing user sessions.
     */
    private final SessionProvider sessionProvider;

    /**
     * Constructs a UserService instance, initializing the UserRepository.
     */
    public UserService() {
        this.userRepository = new UserRepository();
        this.sessionProvider = SessionProvider.getInstance();
    }

    /**
     * Retrieves all users.
     *
     * @return A list of all users.
     */
    public List<User> getUsers() {
        return this.userRepository.findAll();
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
     * @return Deleted user
     */
    public User deleteUser() {
        if(this.sessionProvider.isAuthenticated() && this.sessionProvider.getAuthenticatedUser() != null){
            return this.userRepository.delete(this.sessionProvider.getAuthenticatedUser());
        }
        else{
            throw new IllegalArgumentException("User does not have permission or is not authenticated.");
        }
    }
}
