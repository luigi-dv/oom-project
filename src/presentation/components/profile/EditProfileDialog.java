package src.presentation.components.profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.domain.entities.User;

import src.presentation.controllers.profile.EditProfileDialogController;
public class EditProfileDialog extends JDialog {
    private final User currentUser;

    private JTextField bioInput;
    private boolean popupShown = false;

    private final EditProfileDialogController controller;

    public EditProfileDialog(JFrame parent, User currentUser) {
        super(parent, "Edit profile", true);
        this.controller = new EditProfileDialogController();
        this.currentUser = currentUser;
        createPopup();
    }

    private void createPopup() {
        JPanel panel = new JPanel(new GridLayout(3,2));
        JLabel bioLabel = new JLabel("New Bio: ");
        JLabel pfpLabel = new JLabel("Profile Picture");
        bioInput = new JTextField(currentUser.getBio());
        JButton pictureButton = createUploadPhotoButton();

        JButton doneButton = new JButton("Done");
        doneButton.addActionListener(e -> showPopup());

        panel.add(bioLabel);
        panel.add(bioInput);
        panel.add(pfpLabel);
        panel.add(pictureButton);
        panel.add(new JLabel());
        panel.add(doneButton);

        getContentPane().add(panel);
        pack();
        setLocationRelativeTo(null);
    }

    private void showPopup() {
        if(!popupShown){
            popupShown = !popupShown;
            setVisible(popupShown);
        } else {
            popupShown = !popupShown;
            setVisible(popupShown);
            // gui.changeScreen(UI.PROFILE);
            controller.setUserBio(currentUser, bioInput.getText());
        }
    }

    /**
     * Creates the button for uploading a profile photo with an action listener.
     *
     * @return The upload photo button.
     */
    private JButton createUploadPhotoButton() {
        JButton uploadPhButton = new JButton("Upload Photo");

        uploadPhButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.handleProfilePictureUpload(currentUser.getUsername());
            }
        });
        return uploadPhButton;
    }
}