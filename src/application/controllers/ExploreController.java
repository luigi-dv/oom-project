package src.application.controllers;

import src.application.services.PictureService;
import src.application.services.SearchService;
import src.application.services.StorageService;
import src.domain.entities.Picture;
import src.domain.interfaces.ISearchable;

import javax.swing.*;
import java.util.List;
import java.util.UUID;

public class ExploreController extends BaseController {

    private final StorageService storageService;
    private final PictureService pictureService;
    private final SearchService searchService;

    public ExploreController() {
        this.storageService = new StorageService();
        this.pictureService = new PictureService();
        this.searchService = new SearchService();
    }

    /**
     * Loads images from the picture service and resizes them to the provided size.
     *
     * @param imageSize The size to which the images should be resized.
     * @return The list of images.
     */
    public List<ImageIcon> loadImages(int imageSize) {
        List<Picture> pictures = pictureService.getAllPictures();
        return storageService.loadImages(pictures, imageSize);
    }

    /**
     * Displays the details of the provided image.
     *
     * @param imageIcon The image for which to display the details.
     */
    public void displayImageDetails(ImageIcon imageIcon) {
        // Code for displaying image details using imageService
        // ...
    }

    public List<Picture> getallPictures() {
        return pictureService.getAllPictures();
    }

    public Picture getPictureById(UUID id) {
        return pictureService.getPictureById(id);
    }

    public List<ISearchable> search(String query) {
        return searchService.search(query).getISearchables();
    }


}
