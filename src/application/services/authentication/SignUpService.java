package src.application.services.authentication;

import java.io.File;
import src.infrastructure.repositories.StorageRepository;

/**
 * Service class responsible for handling user registration and authentication during the sign-up process.
 * This class extends the base AuthenticationService, providing additional functionality for sign-up operations.
 */
public class SignUpService extends AuthenticationService {

    /**
     * The storage repository
     */
    private final StorageRepository repository;

    /**
     * Constructs an instance of SignUpService, initializing the StorageRepository.
     */
    public SignUpService() {
        repository = new StorageRepository();
    }

    /**
     * Saves the user's profile picture during the sign-up process.
     * This method utilizes the StorageRepository to store the profile picture associated with the specified username.
     *
     * @param file     The file representing the user's profile picture to be saved.
     * @param username The username of the user for whom the profile picture is being saved.
     */
    public boolean saveProfilePicture(File file, String username) {
        return repository.saveProfilePicture(file, username);
    }
}
