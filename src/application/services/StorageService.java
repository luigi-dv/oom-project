package src.application.services;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import src.domain.entities.Picture;

public class StorageService {

    /**
     * Loads images from the provided list of pictures and resizes them to the provided size.
     *
     * @param pictures The list of pictures from which to load the images.
     * @param imageSize The size to which the images should be resized.
     * @return The list of images.
     */
    public List<ImageIcon> loadImages(List<Picture> pictures, int imageSize) {
        return pictures.stream()
                .map(picture -> {
                    File imageFile = new File(picture.getImagePath());
                    return new ImageIcon(Objects.requireNonNull(getResizedImage(imageFile, imageSize)));
                })
                .toList();
    }

    /**
     * Returns a resized image from the provided file.
     *
     * @param imageFile The file from which to read the image.
     * @param imageSize The size to which the image should be resized.
     * @return The resized image.
     */
    private Image getResizedImage(File imageFile, int imageSize) {
        try {
            BufferedImage originalImage = ImageIO.read(imageFile);
            return originalImage.getScaledInstance(imageSize, imageSize, Image.SCALE_SMOOTH);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception according to your application's requirements.
            return null;
        }
    }
}
