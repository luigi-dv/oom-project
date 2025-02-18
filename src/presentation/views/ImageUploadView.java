package src.presentation.views;

import src.presentation.Router;
import src.presentation.components.errors.ErrorComponent;
import src.presentation.controllers.ImageUploadController;
import src.presentation.interfaces.IImageUploadUI;
import src.presentation.interfaces.UIConstants;
import src.domain.entities.User;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;

public class ImageUploadView extends JPanel {

    private JLabel imagePreviewLabel;
    private JTextField captionTextArea;
    private JButton uploadButton;
    private JTextField hashTagsTextField;
    private ErrorComponent errorComponent;
    private final ImageUploadController controller;
    private User user;
    private JPanel contentPanel;

    public ImageUploadView(Router router) {
        this.controller = new ImageUploadController();
        user = controller.getAuthenticatedUser();
        this.errorComponent = new ErrorComponent("");
        setSize(UIConstants.WIDTH, UIConstants.HEIGHT);
        setMinimumSize(new Dimension(UIConstants.WIDTH, UIConstants.HEIGHT));
        setLayout(new BorderLayout());
        initializeUI();
    }

    private void initializeUI() {
        // Main content panel
        contentPanel = createContentPanel();
        add(contentPanel);
    }

    private JPanel createContentPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        // Image preview
        JPanel imagePreviewPanel = new JPanel();
        imagePreviewPanel.setBorder(BorderFactory.createTitledBorder("Image Preview")); // Add a title border
        imagePreviewLabel = IImageUploadUI.createImagePreviewLabel(UIConstants.WIDTH, UIConstants.HEIGHT);
        imagePreviewPanel.add(imagePreviewLabel);
        contentPanel.add(imagePreviewPanel, BorderLayout.NORTH);

        JPanel bioAndHashtagsPanel = new JPanel();
        bioAndHashtagsPanel.setLayout(new GridLayout(6, 1, 0, 10));

        JLabel bioLabel = new JLabel("Caption:");
        captionTextArea = IImageUploadUI.createCaptionTextField();
        bioLabel.setLabelFor(captionTextArea);

        JLabel hashTagsLabel = new JLabel("Hashtags:");
        hashTagsTextField = IImageUploadUI.createHashTagsTextField();
        hashTagsLabel.setLabelFor(hashTagsTextField);

        uploadButton = IImageUploadUI.createUploadButton();
        uploadButton.addActionListener(this::uploadAction);

        bioAndHashtagsPanel.add(bioLabel);
        bioAndHashtagsPanel.add(captionTextArea);
        bioAndHashtagsPanel.add(hashTagsLabel);
        bioAndHashtagsPanel.add(hashTagsTextField);
        bioAndHashtagsPanel.add(uploadButton);
        bioAndHashtagsPanel.add(errorComponent);

        contentPanel.add(bioAndHashtagsPanel, BorderLayout.CENTER);

        return contentPanel;
    }

    private void uploadAction(ActionEvent event) {

        if (captionTextArea.getText().isEmpty()) {
            errorComponent.displayErrorMessage("Please enter a caption");
            contentPanel.revalidate();
            contentPanel.repaint();
            return;
        }

        if (hashTagsTextField.getText().isEmpty()) {
            errorComponent.displayErrorMessage("Please enter hashtags");
            contentPanel.revalidate();
            contentPanel.repaint();
            return;
        }

        handleFileUpload();
    }

    private void handleFileUpload() {
        JFileChooser fileChooser = createFileChooser();
    
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                String newFileName = copyAndSaveImage(selectedFile);
                ImageIcon imageIcon = loadImagePreview(newFileName);
                updateImagePreview(imageIcon);
                errorComponent.displaySuccessMessage("Image uploaded successfully");
            } catch (IOException ex) {
                handleFileUploadError(ex);
            }
        }
    }

    private JFileChooser createFileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select an image file");
        fileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "png", "jpg", "jpeg");
        fileChooser.addChoosableFileFilter(filter);
        return fileChooser;
    }
    
    private String copyAndSaveImage(File selectedFile) throws IOException {
        String username = user.getUsername();
        int imageId = getNextImageId(user);
        String fileExtension = getFileExtension(selectedFile);
        String newFileName = getNewFileName(username, imageId, fileExtension);
        Path destPath = Paths.get("resources/storage/uploaded", newFileName);
        Files.copy(selectedFile.toPath(), destPath, StandardCopyOption.REPLACE_EXISTING);
        saveImageInfo(user, newFileName, captionTextArea.getText(), hashTagsTextField.getText());
        return newFileName;
    }
    
    private ImageIcon loadImagePreview(String fileName) {
        ImageIcon imageIcon = new ImageIcon("resources/storage/uploaded/" + fileName);
        Image image = imageIcon.getImage();
        int previewWidth = imagePreviewLabel.getWidth();
        int previewHeight = imagePreviewLabel.getHeight();
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
        double widthRatio = (double) previewWidth / imageWidth;
        double heightRatio = (double) previewHeight / imageHeight;
        double scale = Math.min(widthRatio, heightRatio);
        int scaledWidth = (int) (scale * imageWidth);
        int scaledHeight = (int) (scale * imageHeight);
        imageIcon.setImage(image.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH));
        return imageIcon;
    }
    
    private void updateImagePreview(ImageIcon imageIcon) {
        if (imagePreviewLabel.getWidth() > 0 && imagePreviewLabel.getHeight() > 0) {
            imagePreviewLabel.setIcon(imageIcon);
        }
    }
    
    private void handleFileUploadError(IOException ex) {
        errorComponent.displayErrorMessage(ex.getMessage());
    }
    

    private String getNewFileName(String username, int imageId, String fileExtension) {
        return username + "_" + imageId + "." + fileExtension;
    }

    private int getNextImageId(User user) {
        return user.getProfile().getPostsCount() + 1;
    }

    private void saveImageInfo(User user, String imagePath, String caption, String hashTags) {
        controller.uploadImage(user, imagePath, caption, hashTags);

    }

    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return ""; // empty extension
        }
        return name.substring(lastIndexOf + 1);
    }
}

