package src.presentation.components.profile;

import java.awt.*;
import javax.swing.*;
import src.domain.entities.User;
import src.presentation.components.buttons.ButtonComponent;
import src.presentation.components.errors.ErrorComponent;
import src.presentation.components.ui.HintTextField;
import src.presentation.controllers.profile.EditProfileDialogController;

public class EditProfileDialog extends JDialog {
    private final User currentUser;
    private JTextField bioInput;
    private boolean popupShown;
    private ErrorComponent errorComponent;
    private JPanel panel;
    private final EditProfileDialogController controller;

    public EditProfileDialog(JFrame parent, User currentUser) {
        super(parent, "Edit profile", true);
        this.controller = new EditProfileDialogController();
        this.currentUser = currentUser;
        this.popupShown = false;
        this.errorComponent = new ErrorComponent();

        panel = createPopup();

        getContentPane().add(panel);
        // getContentPane().add(errorComponent, BorderLayout.SOUTH);
        pack();
        // Increase the size of the dialog
        setSize(400, 200); // Adjust the width and height as needed
        setLocationRelativeTo(null);
    }

    private JPanel createPopup() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 10)); // Increased rows to 4
        JLabel bioLabel = new JLabel("New Bio: ");
        JLabel pfpLabel = new JLabel("Profile Picture");
        bioInput = new HintTextField(currentUser.getBio());
        JButton pictureButton = createUploadPhotoButton();
    
        ButtonComponent doneButton = new ButtonComponent("Done", 14, 5, Component.CENTER_ALIGNMENT, "primary", false);
        doneButton.addActionListener(e -> togglePopup());
    
        panel.add(bioLabel);
        panel.add(bioInput);
        panel.add(pfpLabel);
        panel.add(pictureButton);
        panel.add(new JLabel());
        panel.add(doneButton);
        panel.add(errorComponent);
    
       return panel;
    }

    private void togglePopup() {
        if(popupShown){
            if (!bioInput.getText().isEmpty()) {
                updateUserBio();
            } else {
                errorComponent.displayErrorMessage("Bio cannot be empty");
                panel.revalidate();
                panel.repaint();
                return;
            }
        }
        toggleVisibility();
    }

    private void toggleVisibility() {
        popupShown = !popupShown;
        setVisible(popupShown);
    }

    private void updateUserBio() {
        controller.setUserBio(currentUser, bioInput.getText());
    }

    /**
     * Creates the button for uploading a profile photo with an action listener.
     *
     * @return The upload photo button.
     */
    private JButton createUploadPhotoButton() {
        ButtonComponent uploadPhButton = new ButtonComponent("Upload Photo", 14, 5, Component.CENTER_ALIGNMENT, "secondary", false);
        uploadPhButton.addActionListener(e -> controller.handleProfilePictureUpload(currentUser.getUsername()));
        return uploadPhButton;
    }
}