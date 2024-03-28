package src.presentation.controllers;

import src.application.services.LikeService;
import src.domain.entities.Picture;

/**
 * Controller for liking pictures
 */
public class PictureController extends BaseController {

        private final LikeService<Picture>  likeServicePicture;

    public PictureController() {
        super();
        this.likeServicePicture = new LikeService<>(sessionProvider);
    }

    /**
     * Like a picture
     * @param picture The picture to like
     * @return True if the picture was liked, false otherwise
     */
    public boolean likePicture(Picture picture) {
        return likeServicePicture.like(picture);
    }


    
}
