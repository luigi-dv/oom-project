package src.presentation.controllers.profile;

import src.application.services.PictureService;
import src.application.services.ProfileService;
import src.domain.entities.Picture;
import src.domain.entities.User;
import src.presentation.controllers.BaseController;

public class PostGridController extends BaseController {

    private final PictureService pictureService;
    private final ProfileService profileService;

    public PostGridController() {
        super();
        this.pictureService = new PictureService();
        this.profileService = new ProfileService();
    }

    public void deletePicture(Picture picture) {
        pictureService.deletePicture(picture);
    }

    
    /**
     * Initialize the profile for a user
     *
     * @param user the user to initialize the profile for
     */
    public void initializeProfile(User user){
        profileService.createProfileFromUser(user);
    }

    
}
