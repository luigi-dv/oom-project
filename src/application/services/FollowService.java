package src.application.services;

import java.util.List;
import src.domain.entities.User;
import src.domain.aggregate.Follow;
import src.infrastructure.repositories.FollowRepository;

public class FollowService {
    /**
     * The picture repository
     */
    private final FollowRepository repository;

    /**
     * Constructor for PictureService
     */
    public FollowService() {
        this.repository = new FollowRepository();
    }

    /**
     * Save a picture
     *
     * @param follow The follow to be saved
     */
    public void save(Follow follow) {
        repository.save(follow);
    }

    /**
     * Get all followers from a user
     * @param user The user to get the follower from
     * @return A list of users
     */
    public List<User> getFollowersFromUser(User user) {
        return repository.findByUser(user);
    }

    /**
     * Get all users that a user is following
     * @param user The user to get the following users from
     * @return A list of users
     */
    public List<User> getFollowingFromUser(User user) {
        return repository.findByFollower(user);
    }
}
