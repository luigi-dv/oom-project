package src.application.views;

import src.application.controllers.ImageUploadController;
import src.application.views.interfaces.IImageUploadUI;
import src.application.views.interfaces.UIConstants;
import src.domain.entities.User;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class ImageUploadUI extends JPanel {

    private JLabel imagePreviewLabel;
    private JTextArea captionTextArea;
    private JButton uploadButton;
    private JButton saveButton;
    // private boolean imageUploaded = false;
    private final ImageUploadController controller;
    private User user;

    public ImageUploadUI(GUI gui, User user) {
        this.user = user;
        this.controller = new ImageUploadController();
        setSize(UIConstants.WIDTH, UIConstants.HEIGHT);
        setMinimumSize(new Dimension(UIConstants.WIDTH, UIConstants.HEIGHT));
        setLayout(new BorderLayout());
        initializeUI();
    }

    private void initializeUI() {
        // Main content panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        // Image preview
        imagePreviewLabel = IImageUploadUI.createImagePreviewLabel(UIConstants.WIDTH, UIConstants.HEIGHT);
        contentPanel.add(imagePreviewLabel);

        // Bio text area
        captionTextArea = IImageUploadUI.createCaptionTextArea();

        JScrollPane bioScrollPane = new JScrollPane(captionTextArea);
        bioScrollPane.setPreferredSize(new Dimension(UIConstants.WIDTH - 50,  UIConstants.HEIGHT / 6));
        contentPanel.add(bioScrollPane);

        // Upload button
        uploadButton = IImageUploadUI.createUploadButton();
        uploadButton.addActionListener(this::uploadAction);
        contentPanel.add(uploadButton);

        // Save button (for bio)
        saveButton = IImageUploadUI.createSaveCaptionButton();
        saveButton.addActionListener(this::saveBioAction);

        // Add panels to frame
        add(contentPanel);
    }

    private void uploadAction(ActionEvent event) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an image file");
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "png", "jpg", "jpeg");
        fileChooser.addChoosableFileFilter(filter);

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                String username = user.getUsername();
                int imageId = getNextImageId(user);
                String fileExtension = getFileExtension(selectedFile);

                String newFileName = getNewFileName(username, imageId, fileExtension);

                Path destPath = Paths.get("resources\\storage\\uploaded", newFileName);
                Files.copy(selectedFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);

                // Save the bio and image ID to a text file
                saveImageInfo(user, newFileName, captionTextArea.getText());

                // Load the image from the saved path
                ImageIcon imageIcon = new ImageIcon(destPath.toString());

                // Check if imagePreviewLabel has a valid size
                if (imagePreviewLabel.getWidth() > 0 && imagePreviewLabel.getHeight() > 0) {
                    Image image = imageIcon.getImage();

                    // Calculate the dimensions for the image preview
                    int previewWidth = imagePreviewLabel.getWidth();
                    int previewHeight = imagePreviewLabel.getHeight();
                    int imageWidth = image.getWidth(null);
                    int imageHeight = image.getHeight(null);
                    double widthRatio = (double) previewWidth / imageWidth;
                    double heightRatio = (double) previewHeight / imageHeight;
                    double scale = Math.min(widthRatio, heightRatio);
                    int scaledWidth = (int) (scale * imageWidth);
                    int scaledHeight = (int) (scale * imageHeight);

                    // Set the image icon with the scaled image
                    imageIcon.setImage(image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH));
                }

                imagePreviewLabel.setIcon(imageIcon);

                // Update the flag to indicate that an image has been uploaded
                // imageUploaded = true;

                // Change the text of the upload button
                uploadButton.setText("Upload Another Image");

                JOptionPane.showMessageDialog(this, "Image uploaded and preview updated!");
            } catch (IOException ex) {
                System.out.println(ex.getLocalizedMessage());
                JOptionPane.showMessageDialog(this, "Error saving image: " + ex.getMessage(), "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String getNewFileName(String username, int imageId, String fileExtension) {
        return username + "_" + imageId + "." + fileExtension;
    }

    private int getNextImageId(User user) {
        return user.getProfile().getPostsCount() + 1;
    }

    private void saveImageInfo(User user, String imagePath, String caption) {
        controller.uplaodImage(user, imagePath, caption);

    }

    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf + 1);
    }

    private void saveBioAction(ActionEvent event) {
        // Here you would handle saving the bio text
        String bioText = captionTextArea.getText();
        // For example, save the bio text to a file or database
        JOptionPane.showMessageDialog(this, "Caption saved: " + bioText);
    }
}

