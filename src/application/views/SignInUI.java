package src.application.views;

import src.application.controllers.SignInController;
import src.application.views.interfaces.IAuthenticationUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * The SignInUI class represents the user interface for the sign-in functionality.
 * It extends JPanel and includes components for entering the username and password,
 * a sign-in button, and an option to register a new account.
 */
public class SignInUI extends JPanel {

    private final GUI gui;
    private JTextField txtUsername;
    private JTextField txtPassword;
    private final SignInController controller;

    /**
     * Constructs a new SignInUI instance.
     *
     * @param gui The main GUI instance.
     */
    public SignInUI(GUI gui) {
        this.gui = gui;
        this.controller = new SignInController();
        initializeUI();
    }

    /**
     * Initializes the user interface components.
     */
    private void initializeUI() {
        JPanel headerPanel = IAuthenticationUI.createHeaderPanel();
        JPanel fieldsPanel = createFieldsPanel();
        JButton signInButton = createSignInButton();
        JPanel registerPanel = createRegisterPanel(signInButton);

        add(headerPanel, BorderLayout.NORTH);
        add(fieldsPanel, BorderLayout.CENTER);
        add(registerPanel, BorderLayout.SOUTH);

        JButton registerNowButton = createRegisterNowButton();
        JPanel buttonPanel = createButtonPanel(signInButton, registerNowButton);

        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    /**
     * Creates a panel containing sign-in and register now buttons.
     *
     * @param signInButton      The sign-in button.
     * @param registerNowButton The register now button.
     * @return The button panel.
     */
    private JPanel createButtonPanel(JButton signInButton, JButton registerNowButton) {
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        buttonPanel.setBackground(Color.white);
        buttonPanel.add(signInButton);
        buttonPanel.add(registerNowButton);
        return buttonPanel;
    }

    /**
     * Creates the register now button.
     *
     * @return The register now button.
     */
    private JButton createRegisterNowButton() {
        JButton registerNowButton = new JButton("No Account? Register Now");
        registerNowButton.addActionListener(this::onRegisterNowClicked);
        registerNowButton.setBackground(Color.WHITE);
        registerNowButton.setForeground(Color.BLACK);
        registerNowButton.setFocusPainted(false);
        registerNowButton.setBorderPainted(false);
        return registerNowButton;
    }

    /**
     * Creates the panel containing the register button.
     *
     * @param signInButton The sign-in button.
     * @return The register panel.
     */
    private JPanel createRegisterPanel(JButton signInButton) {
        JPanel registerPanel = new JPanel(new BorderLayout());
        registerPanel.setBackground(Color.WHITE);
        registerPanel.add(signInButton, BorderLayout.CENTER);
        return registerPanel;
    }

    /**
     * Creates the sign-in button.
     *
     * @return The sign-in button.
     */
    private JButton createSignInButton() {
        JButton signInButton = new JButton("Sign-In");
        signInButton.addActionListener(e -> {
            try {
                onSignInClicked(e);
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        });
        signInButton.setBackground(new Color(255, 90, 95));
        signInButton.setForeground(Color.BLACK);
        signInButton.setFocusPainted(false);
        signInButton.setBorderPainted(false);
        signInButton.setFont(new Font("Arial", Font.BOLD, 14));
        return signInButton;
    }

    /**
     * Creates the panel containing text fields for username and password.
     *
     * @return The fields panel.
     */
    private JPanel createFieldsPanel() {
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

    /**
     * Handles the sign-in button click event.
     *
     * @param event The ActionEvent triggered by the sign-in button.
     * @throws Exception If an error occurs during sign-in.
     */
    private void onSignInClicked(ActionEvent event) throws Exception {
        String enteredUsername = txtUsername.getText();
        String enteredPassword = txtPassword.getText();
        if (controller.signIn(enteredUsername, enteredPassword)) {
            gui.changeScreen(UI.PROFILE);
            return;
        }
        // TODO: Show error message
    }

    /**
     * Handles the register now button click event.
     *
     * @param event The ActionEvent triggered by the register now button.
     */
    private void onRegisterNowClicked(ActionEvent event) {
        gui.changeAuthenticationScreen(UI.SIGNUP);
    }
}
