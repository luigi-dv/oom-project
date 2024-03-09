package src.application.views;

import src.application.controllers.SignUpController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpUI extends JPanel {

    private final int WIDTH;
    private final int HEIGHT;
    private final GUI gui;

    private JTextField txtUsername;
    private JTextField txtPassword;
    private JTextField txtBio;
    private JButton btnRegister;
    private JLabel lblPhoto;
    private JButton btnUploadPhoto;
    private JButton btnSignIn;

    private final SignUpController controller;

    public SignUpUI(int width, int height, GUI gui) {
        this.controller = new SignUpController();
        WIDTH = width;
        HEIGHT = height;
        this.gui = gui;
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
        btnUploadPhoto = new JButton("Upload Photo");

        btnUploadPhoto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.handleProfilePictureUpload(txtUsername.getText());
            }
        });
        JPanel photoUploadPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        photoUploadPanel.add(btnUploadPhoto);
        fieldsPanel.add(photoUploadPanel);

        // Register button with black text
        btnRegister = new JButton("Register");
        btnRegister.addActionListener(this::onRegisterClicked);
        btnRegister.setBackground(new Color(255, 90, 95)); // Use a red color that matches the mockup
        btnRegister.setForeground(Color.BLACK); // Set the text color to black
        btnRegister.setFocusPainted(false);
        btnRegister.setBorderPainted(false);
        btnRegister.setFont(new Font("Arial", Font.BOLD, 14));
        JPanel registerPanel = new JPanel(new BorderLayout()); // Panel to contain the register button
        registerPanel.setBackground(Color.WHITE); // Background for the panel
        registerPanel.add(btnRegister, BorderLayout.CENTER);

        // Adding components to the frame
        add(headerPanel, BorderLayout.NORTH);
        add(fieldsPanel, BorderLayout.CENTER);
        // Adding the sign in button to the register panel or another suitable panel
        btnSignIn = new JButton("Already have an account? Sign In");
        btnSignIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSignInUI();
            }
        });
        registerPanel.add(btnSignIn, BorderLayout.SOUTH);
        add(registerPanel, BorderLayout.SOUTH);
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
//                    SwingUtilities.invokeLater(() -> {
//                        SignInUI signInFrame = new SignInUI(WIDTH, HEIGHT, gui);
//                        signInFrame.setVisible(true);
//                    });
                }
            }
        }
    }

    private void openSignInUI() {
        // Close the SignUpUI frame

        gui.changeAuthenticationScreen(UI.SIGNIN);

        // Open the SignInUI frame
        // SwingUtilities.invokeLater(() -> {
        //     SignInUI signInFrame = new SignInUI(WIDTH, HEIGHT, gui);
        //     signInFrame.setVisible(true);
        // });
    }

}

