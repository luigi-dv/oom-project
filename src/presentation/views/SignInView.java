package src.presentation.views;

import src.presentation.Router;
import src.presentation.components.buttons.ButtonComponent;
import src.presentation.components.errors.ErrorComponent;
import src.presentation.components.ui.HintPasswordField;
import src.presentation.controllers.SignInController;
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
    private ErrorComponent errorMessage;
    private JPanel fieldsJPanel;
    private final SignInController controller;
    private final Router router;

    /**
     * Constructs a new SignInUI instance.
     */
    public SignInView(Router router) {
        this.controller = new SignInController();
        this.router = router;
        errorMessage = new ErrorComponent();
        initializeUI();
    }

    /**
     * Initializes the user interface components.
     */
    private void initializeUI() {
        fieldsJPanel = createFieldsPanel();
        add(fieldsJPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    /**
     * Creates the register now button.
     *
     * @return The register now button.
     */
    private JButton createRegisterNowButton() {
        ButtonComponent registerNowButton = new ButtonComponent("Register Now", 14, 5, Component.CENTER_ALIGNMENT, "secondary", false);
        registerNowButton.addActionListener(e -> onRegisterNowClicked(e));
        return registerNowButton;
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
        JPanel buttonsPanel = createButtonPanel();

        // Add the text fields panel and buttons panel to the main panel
        fieldsPanel.add(textFieldsPanel, BorderLayout.NORTH);
        fieldsPanel.add(buttonsPanel, BorderLayout.CENTER);
        

        return fieldsPanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0; // Allow the button to expand horizontally
        gbc.insets = new Insets(10, 0, 0, 0); // Add padding between the button and the inputs

        // Create the sign-in button
        JButton signInButton = createSignInButton();
        JButton registerNowButton = createRegisterNowButton();
        gbc.gridy = 0;
        buttonsPanel.add(signInButton, gbc);
        gbc.gridy = 1;
        buttonsPanel.add(registerNowButton, gbc);
        gbc.gridy = 2;
        buttonsPanel.add(errorMessage, gbc);
        return buttonsPanel;
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
        System.out.println("Username: " + enteredUsername + ", Password: " + enteredPassword.isEmpty());
        if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
            errorMessage.displayErrorMessage("Please enter a username and password.");
            fieldsJPanel.revalidate();
            fieldsJPanel.repaint();
            return;
        }

        if (controller.signIn(enteredUsername, enteredPassword)) {
            router.switchTo(UIViews.PROFILE);
        } else {
            errorMessage.displayErrorMessage("Invalid username or password.");
            fieldsJPanel.revalidate();
            fieldsJPanel.repaint();
        }
    }

    /**
     * Handles the register now button click event.
     *
     * @param event The ActionEvent triggered by the register now button.
     */
    private void onRegisterNowClicked(ActionEvent event) {
        router.switchTo(UIViews.SIGNUP);
    }
}
