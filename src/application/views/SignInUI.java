package src.application.views;

import src.application.controllers.SignInController;
import src.domain.entities.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SignInUI extends JPanel {

    private final int WIDTH;
    private final int HEIGHT;
    private final GUI gui;
    private JTextField txtUsername;
    private JTextField txtPassword;
    private JButton btnSignIn, btnRegisterNow;
    private JLabel lblPhoto;

    private final SignInController controller;

    public SignInUI(int width, int height, GUI gui) {
        WIDTH = width;
        HEIGHT = height;
        this.gui = gui;
        this.controller = new SignInController();
        initializeUI();
    }

    private void initializeUI() {
        // Header with the Register label
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(new Color(51, 51, 51)); // Set a darker background for the header
        JLabel lblRegister = new JLabel("Quackstagram 🐥");
        lblRegister.setFont(new Font("Arial", Font.BOLD, 16));
        lblRegister.setForeground(Color.WHITE); // Set the text color to white
        headerPanel.add(lblRegister);
        headerPanel.setPreferredSize(new Dimension(WIDTH, 40)); // Give the header a fixed height

        // Profile picture placeholder without border
        lblPhoto = new JLabel();
        lblPhoto.setPreferredSize(new Dimension(80, 80));
        lblPhoto.setHorizontalAlignment(JLabel.CENTER);
        lblPhoto.setVerticalAlignment(JLabel.CENTER);
        lblPhoto.setIcon(new ImageIcon(
                new ImageIcon("resources/imageslogos/DACS.png").getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        JPanel photoPanel = new JPanel(); // Use a panel to center the photo label
        photoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        photoPanel.add(lblPhoto);

        // Text fields panel
        JPanel fieldsPanel = new JPanel();
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

        // Register button with black text
        btnSignIn = new JButton("Sign-In");
        btnSignIn.addActionListener(e -> {
            try {
                onSignInClicked(e);
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        });
        btnSignIn.setBackground(new Color(255, 90, 95)); // Use a red color that matches the mockup
        btnSignIn.setForeground(Color.BLACK); // Set the text color to black
        btnSignIn.setFocusPainted(false);
        btnSignIn.setBorderPainted(false);
        btnSignIn.setFont(new Font("Arial", Font.BOLD, 14));
        JPanel registerPanel = new JPanel(new BorderLayout()); // Panel to contain the register button
        registerPanel.setBackground(Color.WHITE); // Background for the panel
        registerPanel.add(btnSignIn, BorderLayout.CENTER);

        // Adding components to the frame
        add(headerPanel, BorderLayout.NORTH);
        add(fieldsPanel, BorderLayout.CENTER);
        add(registerPanel, BorderLayout.SOUTH);

        // New button for navigating to SignUpUI
        btnRegisterNow = new JButton("No Account? Register Now");
        btnRegisterNow.addActionListener(this::onRegisterNowClicked);
        btnRegisterNow.setBackground(Color.WHITE); // Set a different color for distinction
        btnRegisterNow.setForeground(Color.BLACK);
        btnRegisterNow.setFocusPainted(false);
        btnRegisterNow.setBorderPainted(false);

        // Panel to hold both buttons
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10)); // Grid layout with 1 row, 2 columns
        buttonPanel.setBackground(Color.white);
        buttonPanel.add(btnSignIn);
        buttonPanel.add(btnRegisterNow);

        // Adding the button panel to the frame
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void onSignInClicked(ActionEvent event) throws Exception {
        String enteredUsername = txtUsername.getText();
        String enteredPassword = txtPassword.getText();
        System.out.println(enteredUsername + " <-> " + enteredPassword);
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

