package src.application.views;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.application.controllers.SignUpController;
import src.application.views.common.HintTextField;
import src.application.views.interfaces.IAuthenticationUI;

/**
 * The SignUpUI class represents the user interface for the sign-up screen.
 */
public class SignUpUI extends JPanel {

    private final GUI gui;
    private JTextField txtUsername;
    private JTextField txtPassword;
    private JTextField txtBio;
    private final SignUpController controller;

    /**
     * Constructor for SignUpUI.
     *
     * @param gui The main GUI instance.
     */
    public SignUpUI(GUI gui) {
        this.controller = new SignUpController();
        this.gui = gui;
        initializeUI();
    }

    /**
     * Initializes the user interface components.
     */
    private void initializeUI() {
        JPanel headerPanel = IAuthenticationUI.createHeaderPanel();
        JPanel fieldsPanel = createFieldsPanel();
        JPanel registerPanel = createRegisterPanel();

        // Adding components to the frame
        add(headerPanel, BorderLayout.NORTH);
        add(fieldsPanel, BorderLayout.CENTER);
        add(registerPanel, BorderLayout.SOUTH);
    }

    /**
     * Creates the sign-in button with an action listener.
     *
     * @return The sign-in button.
     */
    private JButton createSignInButton() {
        JButton signInButton = new JButton("Already have an account? Sign In");
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSignInUI();
            }
        });
        return signInButton;
    }

    /**
     * Creates the register button with styling and an action listener.
     *
     * @return The register button.
     */
    private JButton createRegisterButton() {
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(this::onRegisterClicked);
        registerButton.setBackground(new Color(255, 90, 95));
        registerButton.setForeground(Color.BLACK);
        registerButton.setFocusPainted(false);
        registerButton.setBorderPainted(false);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        return registerButton;
    }

    /**
     * Creates the panel containing the register button and sign-in link.
     *
     * @return The register panel.
     */
    private JPanel createRegisterPanel() {
        JPanel registerPanel = new JPanel(new BorderLayout());
        registerPanel.setBackground(Color.WHITE);
        JButton registerButton = createRegisterButton();
        registerPanel.add(registerButton, BorderLayout.CENTER);
        JButton signInButton = createSignInButton();
        registerPanel.add(signInButton, BorderLayout.SOUTH);
        return registerPanel;
    }

    /**
     * Creates the panel containing input fields for user registration.
     *
     * @return The fields panel.
     */
    private JPanel createFieldsPanel() {
        JPanel fieldsPanel = new JPanel();
        JPanel photoPanel = IAuthenticationUI.createPhotoPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));

        // Text fields for username, password, and bio
        txtUsername = new HintTextField("Username");
        txtPassword = new HintTextField("Password");
        txtBio = new HintTextField("Bio");
        txtBio.setForeground(Color.GRAY);
        txtUsername.setForeground(Color.GRAY);
        txtPassword.setForeground(Color.GRAY);

        // Adding components to the fields panel
        fieldsPanel.add(Box.createVerticalStrut(10));
        fieldsPanel.add(photoPanel);
        fieldsPanel.add(Box.createVerticalStrut(10));
        fieldsPanel.add(txtUsername);
        fieldsPanel.add(Box.createVerticalStrut(10));
        fieldsPanel.add(txtPassword);
        fieldsPanel.add(Box.createVerticalStrut(10));
        fieldsPanel.add(txtBio);

        // Adding photo upload panel
        JPanel photoUploadPanel = createPhotoUploadPanel();
        fieldsPanel.add(photoUploadPanel);
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
        JButton uploadPhButton = new JButton("Upload Photo");

        uploadPhButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.handleProfilePictureUpload(txtUsername.getText());
            }
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
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            // Attempting to register the user
            if (!controller.register(username, password, bio)) {
                JOptionPane.showMessageDialog(this, "Username already exists. Please choose a different username.", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                // Successful registration, switch to the profile screen
                gui.changeScreen(UI.PROFILE);
            }
        }
    }

    /**
     * Opens the sign-in UI screen.
     */
    private void openSignInUI() {
        gui.changeAuthenticationScreen(UI.SIGNIN);
    }
}