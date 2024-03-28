package src.presentation.controllers;

import src.application.services.LikeService;
import src.domain.entities.Picture;

public class PictureController extends BaseController {

        private final LikeService<Picture>  likeServicePicture;

    public PictureController() {
        super();
        this.likeServicePicture = new LikeService<>(sessionProvider);
    }

    public boolean likePicture(Picture picture) {
        return likeServicePicture.like(picture);
    }


    
}
