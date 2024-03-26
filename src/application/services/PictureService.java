package src.application.services;

import src.domain.entities.Picture;
import src.domain.entities.User;
import src.infrastructure.repositories.LikeRepository;
import src.infrastructure.repositories.PictureRepository;

import java.util.List;
import java.util.UUID;

public class PictureService {

    /**
     * The picture repository
     */
    private final PictureRepository repository;
    private final LikeRepository<Picture> likeRepositoryPicture;

    /**
     * Constructor for PictureService
     */
    public PictureService() {
        this.repository = new PictureRepository();
        this.likeRepositoryPicture = new LikeRepository<>();
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
        List<Picture> pictures = repository.findAll();
        for (Picture picture : pictures) {
            picture.setLikes(likeRepositoryPicture.findByPostId(picture.getId()));
        }
        return pictures;
    }

    public Picture getPictureById(UUID id) {
        return repository.findById(id);
    }

    public void deletePicture(Picture picture) {
        repository.delete(picture);
    }
}
