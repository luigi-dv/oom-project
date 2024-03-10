package src.application.views;

import src.application.controllers.SignInController;
import src.application.views.interfaces.IAuthenticationUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SignInUI extends JPanel {

    private final GUI gui;
    private JTextField txtUsername;
    private JTextField txtPassword;

    private final SignInController controller;

    public SignInUI(GUI gui) {
        this.gui = gui;
        this.controller = new SignInController();
        initializeUI();
    }

    private void initializeUI() {
        // Header with the Register label
        JPanel headerPanel = IAuthenticationUI.createHeaderPanel(); // Give the header a fixed height
        JPanel fieldsPanel = createFeldsPanel();
        JButton signInButton = createSignInButton();
        JPanel registerPanel = createRegisterPanel(signInButton);

        // Adding components to the frame
        add(headerPanel, BorderLayout.NORTH);
        add(fieldsPanel, BorderLayout.CENTER);
        add(registerPanel, BorderLayout.SOUTH);

        JButton registerNowButton = createRegisterNowButton();
        JPanel buttonPanel = createButtonPanel(signInButton, registerNowButton);

        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private JPanel createButtonPanel(JButton signInButton, JButton registerNowButton) {
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10)); // Grid layout with 1 row, 2 columns
        buttonPanel.setBackground(Color.white);
        buttonPanel.add(signInButton);
        buttonPanel.add(registerNowButton);
        return buttonPanel;
    }

    private JButton createRegisterNowButton() {
        JButton registerNowButton = new JButton("No Account? Register Now");
        registerNowButton.addActionListener(this::onRegisterNowClicked);
        registerNowButton.setBackground(Color.WHITE); // Set a different color for distinction
        registerNowButton.setForeground(Color.BLACK);
        registerNowButton.setFocusPainted(false);
        registerNowButton.setBorderPainted(false);
        return registerNowButton;
    }

    private JPanel createRegisterPanel(JButton signInButton) {
        JPanel registerPanel = new JPanel(new BorderLayout()); // Panel to contain the register button
        registerPanel.setBackground(Color.WHITE); 
        registerPanel.add(signInButton, BorderLayout.CENTER);
        return registerPanel;
    }

    private JButton createSignInButton() {
        JButton signInButton = new JButton("Sign-In");
        signInButton.addActionListener(e -> {
            try {
                onSignInClicked(e);
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        });
        signInButton.setBackground(new Color(255, 90, 95)); // Use a red color that matches the mockup
        signInButton.setForeground(Color.BLACK); // Set the text color to black
        signInButton.setFocusPainted(false);
        signInButton.setBorderPainted(false);
        signInButton.setFont(new Font("Arial", Font.BOLD, 14));
        return signInButton;
    }

    private JPanel createFeldsPanel() {
        JPanel fieldsPanel = new JPanel();
        JPanel photoPanel = IAuthenticationUI.createPhotoPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS));
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));

        txtUsername = new JTextField("Username");
        txtPassword = new JTextField("Password");
        txtUsername.setForeground(Color.GRAY);
        txtPassword.setForeground(Color.GRAY);

        fieldsPanel.add(Box.createVerticalStrut(10));
        fieldsPanel.add(photoPanel);
        fieldsPanel.add(Box.createVerticalStrut(10));
        fieldsPanel.add(txtUsername);
        fieldsPanel.add(Box.createVerticalStrut(10));
        fieldsPanel.add(txtPassword);
        fieldsPanel.add(Box.createVerticalStrut(10));
        return fieldsPanel;
    }


    private void onSignInClicked(ActionEvent event) throws Exception {
        String enteredUsername = txtUsername.getText();
        String enteredPassword = txtPassword.getText();
        if (controller.signIn(enteredUsername, enteredPassword)) {
            gui.changeScreen(UI.PROFILE);
           return;
        }
        // TODO: Show error message
        return;
    }

    private void onRegisterNowClicked(ActionEvent event) {
        gui.changeAuthenticationScreen(UI.SIGNUP);
    }
}

