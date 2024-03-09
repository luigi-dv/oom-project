package src.application.services;

import src.domain.entities.Picture;
import src.domain.entities.User;
import src.infrastructure.repositories.PictureRepository;
import src.infrastructure.utilities.storage.PictureStorage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
     * Update a picture
     *
     * @param picture The picture to be uploaded
     */
    public void updatePicture(Picture picture) {
        repository.update(picture);
    }

    /**
     * Get all pictures from a user
     *
     * @param user The user to get the pictures from
     * @return A list of pictures from the user
     */
    public List<Picture> getPicturesFromUser(User user) {
        return repository.findByUser(user);
    }

    /**
     * Get all pictures from followed users
     *
     * @param user The user to get the pictures from
     * @return A list of pictures from the followed users
     */
    public List<Picture> getPicturesFromFollowedUsers(User user) {
        return repository.findByFollowedUsers(user);
    }

    public List<Picture> getAllPictures() {
        return repository.findAll();
    }
}
