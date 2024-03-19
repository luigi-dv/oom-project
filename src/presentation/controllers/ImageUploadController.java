package src.presentation.controllers;

import src.application.services.PictureService;
import src.domain.entities.Picture;
import src.domain.entities.User;

public class ImageUploadController extends BaseController{

    private final PictureService pictureService;

    public ImageUploadController() {
        this.pictureService = new PictureService();
    }
    public void uploadImage(User user, String imagePath, String caption) {
        pictureService.savePicture(new Picture(user, imagePath, caption));
    }

}
