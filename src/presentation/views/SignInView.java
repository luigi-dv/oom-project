package src.presentation.views;

import src.presentation.Router;
import src.presentation.components.buttons.ButtonComponent;
import src.presentation.components.ui.HintPasswordField;
import src.presentation.controllers.SignInController;
import src.presentation.interfaces.IAuthenticationUI;
import src.presentation.components.ui.HintTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * The SignInUI class represents the user interface for the sign-in functionality.
 * It extends JPanel and includes components for entering the username and password,
 * a sign-in button, and an option to register a new account.
 */
public class SignInView extends JPanel {
    private JTextField txtUsername;
    private JTextField txtPassword;
    private final SignInController controller;

    private final Router router;

    /**
     * Constructs a new SignInUI instance.
     */
    public SignInView(Router router) {
        this.controller = new SignInController();
        this.router = router;
        initializeUI();
    }

    /**
     * Initializes the user interface components.
     */
    private void initializeUI() {
        JPanel fieldsPanel = createFieldsPanel();
        JButton signInButton = createSignInButton();
        JPanel registerPanel = createRegisterPanel(signInButton);

        add(fieldsPanel, BorderLayout.CENTER);
        //add(registerPanel, BorderLayout.SOUTH);

        JButton registerNowButton = createRegisterNowButton();
        // JPanel buttonPanel = createButtonPanel(signInButton, registerNowButton);

        // add(buttonPanel, BorderLayout.SOUTH);
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
        ButtonComponent signInButton = new ButtonComponent("Sign-In", 14, 5, Component.CENTER_ALIGNMENT, "primary", false);
        signInButton.addActionListener(e -> {
            try {
                onSignInClicked(e);
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        });
        return signInButton;
    }

    /**
     * Creates the panel containing text fields for username and password.
     *
     * @return The fields panel.
     */
    private JPanel createFieldsPanel() {
        JPanel fieldsPanel = new JPanel(new BorderLayout());
        fieldsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create a panel for the text fields
        JPanel textFieldsPanel = new JPanel();
        textFieldsPanel.setLayout(new GridLayout(2, 1, 0, 10)); // Two rows, one column

        // Create and customize the text fields
        txtUsername = new HintTextField("Username");
        txtPassword = new HintPasswordField("Password");
        txtUsername.setForeground(Color.GRAY);
        txtPassword.setForeground(Color.GRAY);

        // Set the preferred size of text fields
        Dimension textFieldSize = new Dimension(200, 30); // Adjust the size as needed
        txtUsername.setPreferredSize(textFieldSize);
        txtPassword.setPreferredSize(textFieldSize);

        // Add the text fields to the panel
        textFieldsPanel.add(txtUsername);
        textFieldsPanel.add(txtPassword);

        // Create a panel for the buttons with GridBagLayout
        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0; // Allow the button to expand horizontally
        gbc.insets = new Insets(10, 0, 0, 0); // Add padding between the button and the inputs

        // Create the sign-in button
        JButton signInButton = createSignInButton();
        buttonsPanel.add(signInButton, gbc);

        // Add the text fields panel and buttons panel to the main panel
        fieldsPanel.add(textFieldsPanel, BorderLayout.CENTER);
        fieldsPanel.add(buttonsPanel, BorderLayout.SOUTH);

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
            router.switchTo("profile");
        }
    }

    /**
     * Handles the register now button click event.
     *
     * @param event The ActionEvent triggered by the register now button.
     */
    private void onRegisterNowClicked(ActionEvent event) {
        router.switchTo("signup");
    }
}
