package src.presentation.controllers;

import java.util.ArrayList;
import java.util.List;

import src.application.services.HashTagService;
import src.application.services.PictureService;
import src.domain.entities.HashTag;
import src.domain.entities.Picture;
import src.domain.entities.User;

/**
 * Controller class for the image upload functionality.
 */
public class ImageUploadController extends BaseController{

    private final PictureService pictureService;
    private final HashTagService hashTagService;

    public ImageUploadController() {
        this.pictureService = new PictureService();
        this.hashTagService = new HashTagService();
    }

    /**
     * Uploads an image to the system.
     * @param user The user uploading the image
     * @param imagePath The path to the image file
     * @param caption The caption for the image
     * @param hashTags The hashtags for the image
     */
    public void uploadImage(User user, String imagePath, String caption, String hashTags) {
        Picture picture = new Picture(user, imagePath, caption);
        String[] hashTagArray = hashTags.split(" ");
        List<HashTag> hashTagList = new ArrayList<>();
        for (String hashTag : hashTagArray) {
            if (hashTag.startsWith("#")) {
                hashTag = hashTag.substring(1);
                hashTagList.add(new HashTag(picture, hashTag));
            }
        }
        
        hashTagService.saveHashTags(hashTagList);
        pictureService.savePicture(picture);
    }

}
