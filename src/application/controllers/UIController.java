package src.application.controllers;

import src.domain.entities.Comment;
import src.domain.entities.Picture;
import src.domain.entities.User;
import src.domain.entities.notifications.Notification;

import java.util.List;

import src.application.services.LikeService;
import src.application.services.PictureService;
import src.application.services.ProfileService;
import src.application.services.UserService;
import src.application.services.NotificationService;

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

    private final LikeService<Comment>  likeServiceComment;

    private final UserService userService;

    private final NotificationService notificationService;

    /**
     * Constructor for the UIController class.
     */
    public UIController() {
        this.profileService = new ProfileService();
        this.pictureService = new PictureService();
        this.likeServiceComment = new LikeService<>(sessionProvider); // Specify the type argument as Comment
        this.likeServicePicture = new LikeService<>(sessionProvider);
        this.userService = new UserService();
        this.notificationService = new NotificationService();
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

    public List<Notification> getNotifications(User user) {
        return notificationService.getNotificationsFromUser(user);
    }

    // public boolean likeComment(Comment comment) {
    //     return likeServiceComment.like(comment);
    // }
}

