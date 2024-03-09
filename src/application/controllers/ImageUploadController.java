package src.application.controllers;

import src.application.services.PictureService;
import src.domain.entities.Picture;
import src.domain.entities.User;

import java.awt.*;
import java.net.UnknownServiceException;

public class ImageUploadController extends BaseController{

    private final PictureService pictureService;

    public ImageUploadController() {
        this.pictureService = new PictureService();
    }
    public void uplaodImage(User user, String imagePath, String caption) {
        pictureService.savePicture(new Picture(user, imagePath, caption));
    }

}
