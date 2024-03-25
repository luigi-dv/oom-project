package src.presentation.interfaces;

import javax.swing.*;

import src.presentation.components.buttons.ButtonComponent;
import src.presentation.components.ui.HintTextField;

import java.awt.*;

public interface IImageUploadUI {

    static JLabel createImagePreviewLabel(int width, int height) {
        JLabel imagePreviewLabel = new JLabel();
        imagePreviewLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imagePreviewLabel.setPreferredSize(new Dimension(width - 100, height / 3));

        // Set an initial empty icon to the imagePreviewLabel
        ImageIcon emptyImageIcon = new ImageIcon();
        imagePreviewLabel.setIcon(emptyImageIcon);
        return imagePreviewLabel;
    }

    static JTextField createCaptionTextField() {
        JTextField captionTextField = new HintTextField("Enter a caption");
        return captionTextField;
    }

    static JTextField createHashTagsTextField() {
        JTextField hashTagsTextField = new HintTextField("Enter hashtags separated by spaces");
        return hashTagsTextField;
    }

    static JButton createUploadButton() {
        ButtonComponent uploadButton = new ButtonComponent("Upload Image", 14, 5, Component.CENTER_ALIGNMENT, "primary", false);
        return uploadButton;
    }

    static JButton createSaveCaptionButton() {
        JButton saveButton = new JButton("Save Caption");
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        return saveButton;
    }

}
