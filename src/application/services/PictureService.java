package src.application.services;

import src.domain.entities.Picture;
import src.domain.entities.User;
import src.infrastructure.repositories.PictureRepository;

import java.util.List;

public class PictureService {

    /**
     * The picture repository
     */
    private final PictureRepository repository;

    /**
     * Constructor for PictureService
     */
    public PictureService() {
        this.repository = new PictureRepository();
    }

    /**
     * Save a picture
     *
     * @param picture The picture to be saved
     */
    public void savePicture(Picture picture) {
        repository.create(picture);
    }

    /**
     * Get all pictures from a user
     * @param user The user to get the pictures from
     * @return A list of pictures from the user
     */
    public List<Picture> getPicturesFromUser(User user) {
        return repository.findByUser(user);
    }
}
