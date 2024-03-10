package src.application.views;

import src.application.controllers.SignUpController;
import src.application.views.interfaces.IAuthenticationUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpUI extends JPanel {

    private final GUI gui;

    private JTextField txtUsername;
    private JTextField txtPassword;
    private JTextField txtBio;
    private final SignUpController controller;

    public SignUpUI(GUI gui) {
        this.controller = new SignUpController();
        this.gui = gui;
        initializeUI();
    }

    private void initializeUI() {
      
        JPanel headerPanel = IAuthenticationUI.createHeaderPanel(); // Give the header a fixed height
        JPanel fieldsPanel = createFieldsPanel();
        JPanel registerPanel = createRegisterPanel();

        // Adding components to the frame
        add(headerPanel, BorderLayout.NORTH);
        add(fieldsPanel, BorderLayout.CENTER);
        add(registerPanel, BorderLayout.SOUTH);
    }

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

    private JButton createRegisterButton() {
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(this::onRegisterClicked);
        registerButton.setBackground(new Color(255, 90, 95)); // Use a red color that matches the mockup
        registerButton.setForeground(Color.BLACK); // Set the text color to black
        registerButton.setFocusPainted(false);
        registerButton.setBorderPainted(false);
        registerButton.setFont(new Font("Arial", Font.BOLD, 14));
        return registerButton;
    }

    private JPanel createRegisterPanel() {
        JPanel registerPanel = new JPanel(new BorderLayout()); // Panel to contain the register button
        registerPanel.setBackground(Color.WHITE);
        JButton registerButton = createRegisterButton(); // Background for the panel
        registerPanel.add(registerButton, BorderLayout.CENTER);
        JButton signInButton = createSignInButton();
        registerPanel.add(signInButton, BorderLayout.SOUTH);
        return registerPanel;
    }

    private JPanel createFieldsPanel() {
        JPanel fieldsPanel = new JPanel();
        JPanel photoPanel = IAuthenticationUI.createPhotoPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));

        txtUsername = new JTextField("Username");
        txtPassword = new JTextField("Password");
        txtBio = new JTextField("Bio");
        txtBio.setForeground(Color.GRAY);
        txtUsername.setForeground(Color.GRAY);
        txtPassword.setForeground(Color.GRAY);

        fieldsPanel.add(Box.createVerticalStrut(10));
        fieldsPanel.add(photoPanel);
        fieldsPanel.add(Box.createVerticalStrut(10));
        fieldsPanel.add(txtUsername);
        fieldsPanel.add(Box.createVerticalStrut(10));
        fieldsPanel.add(txtPassword);
        fieldsPanel.add(Box.createVerticalStrut(10));
        fieldsPanel.add(txtBio);
        
        JPanel photoUploadPanel = createPhotoUploadPanel();
        fieldsPanel.add(photoUploadPanel);
        return fieldsPanel;
    }

    private JPanel createPhotoUploadPanel() {
        JPanel photoUploadPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton uploadPhotoButton = createUploadPhotoButton();
        photoUploadPanel.add(uploadPhotoButton);
        return photoUploadPanel;
    }

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

        if (username.isEmpty() || password.isEmpty() || bio.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        else{
            if (username.equals("Username") || password.equals("Password") || bio.equals("Bio")) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else {
                if (!controller.register(username, password, bio)) {
                    JOptionPane.showMessageDialog(this, "Username already exists. Please choose a different username.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    gui.changeScreen(UI.PROFILE);
                }
            }
        }
    }

    private void openSignInUI() {
        gui.changeAuthenticationScreen(UI.SIGNIN);
    }

}

