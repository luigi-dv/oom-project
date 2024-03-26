package src.presentation.controllers.profile;

import src.application.services.PictureService;
import src.domain.entities.Picture;
import src.presentation.controllers.BaseController;

public class PostGridController extends BaseController {

    private final PictureService pictureService;

    public PostGridController() {
        super();
        this.pictureService = new PictureService();
    }

    public void deletePicture(Picture picture) {
        pictureService.deletePicture(picture);
    }

    
}
