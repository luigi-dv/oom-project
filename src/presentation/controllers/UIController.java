package src.presentation.controllers;

import src.domain.entities.Picture;
import src.domain.entities.User;

import java.util.List;

import src.application.services.LikeService;
import src.application.services.PictureService;
import src.application.services.ProfileService;
import src.application.services.UserService;

/**
 * The UIController class is responsible for handling the UI logic.
 */
public class UIController extends BaseController {

    /**
     * The profile service to be used by the UIController.
     */
    private final ProfileService profileService;

    private final PictureService pictureService;

    private final LikeService<Picture>  likeServicePicture;

    private final UserService userService;

    /**
     * Constructor for the UIController class.
     */
    public UIController() {
        this.profileService = new ProfileService();
        this.pictureService = new PictureService();
        this.likeServicePicture = new LikeService<>(sessionProvider);
        this.userService = new UserService();
    }

    /**
     * Initialize the profile for a user
     *
     * @param user the user to initialize the profile for
     */
    public void initializeProfile(User user){
        profileService.createProfileFromUser(user);
    }

    public void setUserBio(User user, String bio) {
        user.setBio(bio);
        userService.updateUser(user);
    }

    public List<Picture> getAllPictures() {
        return pictureService.getAllPictures();
    }

    public boolean likePicture(Picture picture) {
        return likeServicePicture.like(picture);
    }
}

