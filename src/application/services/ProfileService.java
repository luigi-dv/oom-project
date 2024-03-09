package src.application.services;

import java.util.List;

import src.domain.aggregate.Profile;
import src.domain.entities.Picture;
import src.domain.entities.User;

/**
 * The `ProfileService` class is responsible for managing user profiles, associating relevant
 * information such as pictures, followers, and following users with a given user's profile.
 * It interacts with the associated `PictureService` and `FollowService` to gather necessary data.
 */
public class ProfileService {

    /**
     * The service responsible for managing user pictures.
     */
    private final PictureService pictureService;

    /**
     * The service responsible for managing user followers and following relationships.
     */
    private final FollowService followService;

    /**
     * Constructs a new `ProfileService` and initializes the required services.
     */
    public ProfileService() {
        this.pictureService = new PictureService();
        this.followService = new FollowService();
    }

    /**
     * Creates a comprehensive profile for a given user by retrieving and associating
     * pictures, followers, and following information.
     *
     * @param user The user for whom the profile is to be created.
     */
    public void createProfileFromUser(User user) {
        // Retrieve pictures, followers, and following information from associated services
        List<Picture> pictures = pictureService.getPicturesFromUser(user);
        List<User> followers = followService.getFollowersFromUser(user);
        List<User> following = followService.getFollowingFromUser(user);

        // Create a new profile and associate it with the user
        Profile profile = new Profile();
        profile.setPictures(pictures);
        profile.setFollowersUser(followers);
        profile.setFollowingUsers(following);
        user.setProfile(profile);
    }
}
