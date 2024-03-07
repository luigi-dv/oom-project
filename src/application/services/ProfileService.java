package src.application.services;

import src.domain.aggregate.Profile;
import src.domain.entities.Picture;
import src.domain.entities.User;

import java.util.List;

public class ProfileService {

    private PictureService pictureService;
    private UserService userService;
    private FollowService followService;

    public ProfileService(){
        this.pictureService = new PictureService();
        this.userService = new UserService();
        this.followService = new FollowService();
    }

    /**
     * Create a profile from a user
     * @param user The user to create the profile from
     */
    public void createProfileFromUser(User user){
        List<Picture> pictures = pictureService.getPicturesFromUser(user);
        List<User> followers = followService.getFollowersFromUser(user);
        List<User> following = followService.getFollowingFromUser(user);
        Profile profile = new Profile();
        profile.setPictures(pictures);
        profile.setFollowersUser(followers);
        profile.setFollowingUsers(following);
        user.setProfile(profile);
    }
}
