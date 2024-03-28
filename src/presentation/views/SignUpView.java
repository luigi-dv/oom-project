package src.presentation.views;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;

import src.presentation.components.buttons.ButtonComponent;
import src.presentation.components.errors.ErrorComponent;
import src.presentation.components.ui.HintPasswordField;
import src.presentation.components.ui.HintTextField;

import src.presentation.Router;
import src.presentation.controllers.SignUpController;

/**
 * The SignUpUI class represents the user interface for the sign-up screen.
 */
public class SignUpView extends JPanel {
    private JTextField txtUsername;
    private JTextField txtPassword;
    private JTextField txtBio;
    private ErrorComponent errorMessage;
    private JPanel fieldsJPanel;
    private final SignUpController controller;

    private boolean pictureUploaded = false;

    private final Router router;

    /**
     * Constructor for SignUpUI.
     */
    public SignUpView(Router router) {
        this.controller = new SignUpController();
        this.router = router;
        this.errorMessage = new ErrorComponent();
        initializeUI();
    }

    /**
     * Initializes the user interface components.
     */
    private void initializeUI() {
        fieldsJPanel = createFieldsPanel();
        add(fieldsJPanel, BorderLayout.CENTER);
    }

    /**
     * Creates the sign-in button with an action listener.
     *
     * @return The sign-in button.
     */
    private JButton createSignInButton() {
        ButtonComponent signInButton = new ButtonComponent("Sign In", 14, 5, Component.CENTER_ALIGNMENT, "secondary", false);
        signInButton.addActionListener(e -> openSignInUI());
        return signInButton;
    }

    /**
     * Creates the register button with styling and an action listener.
     *
     * @return The register button.
     */
    private JButton createRegisterButton() {
        ButtonComponent registerNowButton = new ButtonComponent("Register Now", 14, 5, Component.CENTER_ALIGNMENT, "primary", false);
        registerNowButton.addActionListener(e -> onRegisterClicked(e));
        return registerNowButton;
    }

    /**
     * Creates the panel containing the register button and sign-in link.
     *
     * @return The register panel.
     */
    private JPanel createButtoPanel() {
        JPanel registerPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0; // Allow the button to expand horizontally
        gbc.insets = new Insets(10, 0, 0, 0); // Add padding between the button and the inputs

        JButton registerButton = createRegisterButton();
        JButton signInButton = createSignInButton();

        gbc.gridy = 0;
        registerPanel.add(registerButton, gbc);
        gbc.gridy = 1;
        registerPanel.add(signInButton, gbc);
        gbc.gridy = 2;
        registerPanel.add(errorMessage, gbc);
        return registerPanel;
    }

    /**
     * Creates the panel containing input fields for user registration.
     *
     * @return The fields panel.
     */
    private JPanel createFieldsPanel() {
        JPanel fieldsPanel = new JPanel(new BorderLayout());
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel textFieldsPanel = new JPanel();
        textFieldsPanel.setLayout(new GridLayout(3, 1, 0, 10)); // Two rows, one column

        txtUsername = new HintTextField("Username");
        txtPassword = new HintPasswordField("Password");
        txtBio = new HintTextField("Bio");
        txtUsername.setForeground(Color.GRAY);
        txtPassword.setForeground(Color.GRAY);
        txtBio.setForeground(Color.GRAY);

        Dimension textFieldSize = new Dimension(200, 30); // Adjust the size as needed
        txtUsername.setPreferredSize(textFieldSize);
        txtPassword.setPreferredSize(textFieldSize);
        txtBio.setPreferredSize(textFieldSize);

        // Add the text fields to the panel
        textFieldsPanel.add(txtUsername);
        textFieldsPanel.add(txtPassword);
        textFieldsPanel.add(txtBio);

        fieldsPanel.add(textFieldsPanel, BorderLayout.NORTH);

        JPanel photoUploadPanel = createPhotoUploadPanel();
        fieldsPanel.add(photoUploadPanel);
        

        JPanel registerPanel = createButtoPanel();
        fieldsPanel.add(registerPanel, BorderLayout.SOUTH);

        

        return fieldsPanel;
    }

    /**
     * Creates the panel containing the photo upload button.
     *
     * @return The photo upload panel.
     */
    private JPanel createPhotoUploadPanel() {
        JPanel photoUploadPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton uploadPhotoButton = createUploadPhotoButton();
        photoUploadPanel.add(uploadPhotoButton);
        return photoUploadPanel;
    }

    /**
     * Creates the button for uploading a profile photo with an action listener.
     *
     * @return The upload photo button.
     */
    private JButton createUploadPhotoButton() {
        ButtonComponent uploadPhButton = new ButtonComponent("Upload Photo", 14, 5, Component.CENTER_ALIGNMENT, "primary", false);
        uploadPhButton.addActionListener(e -> {
            pictureUploaded = controller.handleProfilePictureUpload(txtUsername.getText());
        });
        return uploadPhButton;
    }

    /**
     * Handles the register button click event.
     *
     * @param event The action event triggering the registration process.
     */
    private void onRegisterClicked(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        String bio = txtBio.getText();

        // Validating user input
        if (username.isEmpty() || password.isEmpty() || bio.isEmpty() ||
                username.equals("Username") || password.equals("Password") || bio.equals("Bio")) {
            showError("Please fill out all fields.");
            return;
        } else if(!pictureUploaded) {
            showError("Please upload a profile picture.");
        } else {
            // Attempting to register the user
            if (!controller.register(username, password, bio)) {
                // Registration failed, display an error message
                errorMessage.displayErrorMessage("Username already exists. Please try again.");
                fieldsJPanel.revalidate();
                fieldsJPanel.repaint();
            } else {
                // Successful registration, switch to the profile screen
                router.switchTo(UIViews.PROFILE);
            }
        }
    }

    private void showError(String error){
        errorMessage.displayErrorMessage(error);
        fieldsJPanel.revalidate();
        fieldsJPanel.repaint();
    }

    /**
     * Opens the sign-in UI screen.
     */
    private void openSignInUI() {
        router.switchTo(UIViews.SIGNIN);
    }
}