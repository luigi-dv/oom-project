package src.application.views.interfaces;

import javax.swing.*;
import java.awt.*;

public interface IImageUploadUI {

    static JLabel createImagePreviewLabel(int width, int height) {
        JLabel imagePreviewLabel = new JLabel();
        imagePreviewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePreviewLabel.setPreferredSize(new Dimension(width, height / 3));

        // Set an initial empty icon to the imagePreviewLabel
        ImageIcon emptyImageIcon = new ImageIcon();
        imagePreviewLabel.setIcon(emptyImageIcon);
        return imagePreviewLabel;
    }

    static JTextArea createCaptionTextArea() {
        JTextArea captionTextArea = new JTextArea("Enter a caption");
        captionTextArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        captionTextArea.setLineWrap(true);
        captionTextArea.setWrapStyleWord(true);
        return captionTextArea;
    }

    static JButton createUploadButton() {
        JButton uploadButton = new JButton("Upload Image");
        uploadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        return uploadButton;
    }

    static JButton createSaveCaptionButton() {
        JButton saveButton = new JButton("Save Caption");
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        return saveButton;
    }

}
