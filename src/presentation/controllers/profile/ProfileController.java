package src.presentation.controllers.profile;

import src.application.services.ProfileService;
import src.application.services.UserService;
import src.domain.entities.User;
import src.presentation.controllers.BaseController;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class ProfileController extends BaseController {

    private final ProfileService service;

    private final UserService userService;

    public ProfileController() {
        this.userService = new UserService();
        this.service = new ProfileService();
        service.createProfileFromUser(getAuthenticatedUser());
    }

    /**
     * Handles the profile picture upload when the register button is clicked.
     *
     * @param username The username of the user for whom the profile picture is being uploaded.
     */
    public void handleProfilePictureUpload(String username) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif");
        fileChooser.setFileFilter(filter);

        int result = fileChooser.showOpenDialog(null); // Use null as the parent component

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            service.saveProfilePicture(selectedFile, username);
        }
    }

    /**
     * Set User Bio
     * @param user The User
     * @param bio The Bio
     */
    public void setUserBio(User user, String bio) {
        user.setBio(bio);
        userService.updateUser(user);
    }

}
