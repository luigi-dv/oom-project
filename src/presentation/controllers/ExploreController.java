package src.presentation.controllers;

import src.application.services.PictureService;
import src.application.services.SearchService;
import src.application.services.StorageService;
import src.domain.entities.Picture;
import src.domain.interfaces.ISearchable;

import javax.swing.*;
import java.util.List;
import java.util.UUID;

/**
 * Controller class for the explore functionality.
 */
public class ExploreController extends BaseController {

    // Services
    private final StorageService storageService;
    private final PictureService pictureService;
    private final SearchService searchService;

    /**
     * Creates a new ExploreController.
     */
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

    /**
     * Gets all pictures.
     *
     * @return The list of all pictures.
     */
    public List<Picture> getallPictures() {
        return pictureService.getAllPictures();
    }

    /**
     * Gets a picture by its unique identifier.
     *
     * @param id The unique identifier of the picture.
     * @return The picture.
     */
    public Picture getPictureById(UUID id) {
        return pictureService.getPictureById(id);
    }

    /**
     * Searches for pictures based on the provided query.
     *
     * @param query The search query.
     * @return The list of pictures that match the query.
     */
    public List<ISearchable> search(String query) {
        return searchService.search(query).getISearchables();
    }


}
