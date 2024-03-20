package src.infrastructure.repositories;

import java.io.File;
import java.util.List;

import src.domain.entities.User;
import src.domain.repositiories.IUserRepository;
import src.infrastructure.utilities.file.reader.CredentialsReader;
import src.infrastructure.utilities.file.writer.CredentialWriter;

/**
 * Repository class responsible for handling user data storage and retrieval in the infrastructure layer.
 */
public class UserRepository implements IUserRepository {

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user to be retrieved.
     * @return The user with the specified username or null if not found.
     */
    public User findByUsername(String username) {
        return CredentialsReader.readUserByUsername(username);
    }

    public List<User> findAll() {
        return CredentialsReader.readAllUsers();
    }

    /**
     * Creates a new user.
     *
     * @param user The user to be created.
     * @return The created user entity.
     */
    public User save(User user) {
        if(CredentialsReader.doesFileExist()) {
            return CredentialWriter.writeToFile(user);
        }
        // TODO: Not found logging catching
        return null;
    }

    /**
     * Updates an existing user.
     *
     * @param user The user with updated information.
     * @return The updated user entity.
     */
    public User update(User user) {
        if(CredentialsReader.doesFileExist()) {
            return CredentialWriter.updateCredentials(user);
        }
        // TODO: Not found logging catching
        return null;
    }

    /**
     * Deletes a user by their username.
     *
     * @param user The User entity to be deleted.
     * @return True if the user was successfully deleted, false otherwise.
     */
    public User delete(User user) {
        if(CredentialsReader.doesFileExist()) {
            return CredentialWriter.deleteCredentials(user);
        }
        // TODO: Not found logging catching
        return null;
    }

    /**
     * Saves the user's profile picture.
     *
     * @param file     The file representing the user's profile picture.
     * @param username The username associated with the profile picture.
     */
    public void saveProfilePicture(File file, String username) {
        // TODO: Implement the logic to save the profile picture to storage.
    }
}
