package src.application.views;

import src.application.controllers.UIController;
import src.domain.entities.User;
import src.infrastructure.utilities.Crypter;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class GUI extends JFrame {

    public Crypter crypter;

    public User currentUser;

    private static final int WIDTH = 300;
    private static final int HEIGHT = 500;
    private static final int NAV_ICON_SIZE = 20; // Corrected static size for bottom icons
    private JPanel navigationPanel; // Panel for the navigation
    private JPanel headerPanel;
    private JLabel headerText;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUI frame = new GUI();
            frame.setVisible(true);
        });
    }
    protected final UIController controller;

    public GUI(){
        controller = new UIController();
        // Stores the current user session
        currentUser = controller.getAuthenticatedUser();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Quackstagram - Register");
        setSize(WIDTH, HEIGHT);
        initEncryption();
        SignInUI panel = new SignInUI(WIDTH, HEIGHT, this);
        add(panel);
        navigationPanel = createNavigationPanel();
        headerPanel = createHeaderPanel();

    }

    private void initEncryption(){
        crypter = new Crypter();
        try {
            if (Files.exists(Path.of(Crypter.filePath))) {
                Crypter.key = crypter.loadSecretKey();
            } else {
                Crypter.key = crypter.generateAndSaveSecretKey();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeScreen(UI panel){
        getContentPane().removeAll();
        repaint();
        JPanel pane = new JPanel();
        boolean header = false;
        boolean footer = false;
        switch (panel) {
            case SIGNIN:
                pane = new SignInUI(WIDTH, HEIGHT, this);
                break;
            case SIGNUP:
                pane = new SignUpUI(WIDTH, HEIGHT, this);
                break;
            case EXPLORE:
                setTitle("Explore");
                pane = new ExploreUI(WIDTH, HEIGHT, this);
                setHeaderText("Explore");
                footer = true;
                header = true;
                break;
            case PROFILE:
                setTitle("DACS Profile");
                pane = new InstagramProfileUI(WIDTH, HEIGHT, this);
                footer = true;
                break;
            case IMAGEUPLOAD:
                setTitle("Upload Image");
                pane = new ImageUploadUI(WIDTH, HEIGHT, this);
                setHeaderText("Upload Image");
                header = true;
                footer = true;
                break;
            case NOTIFICATIONS:
                setTitle("Notifications");
                pane = new NotificationsUI(WIDTH, HEIGHT, this);
                setHeaderText("Notifications");
                header = true;
                footer = true;
                break;
            case HOME:
                setTitle("Quakstagram Home");
                pane = new QuakstagramHomeUI(WIDTH, HEIGHT, this);
                setHeaderText("Quackstagram");
                header = true;
                footer = true;
                break;
            default:
                break;
        }
        if(header){
            add(headerPanel, BorderLayout.NORTH);
        }
        add(pane, BorderLayout.CENTER);
        if(footer){
            add(navigationPanel, BorderLayout.SOUTH);
        }
        setVisible(true);
    }

    public void changeScreen(UI panel, User user){
        getContentPane().removeAll();
        repaint();

        if(currentUser == null){
            currentUser = user;
        }

        JPanel pane = new InstagramProfileUI(WIDTH, HEIGHT, this, user);

        add(pane, BorderLayout.NORTH);
        add(navigationPanel, BorderLayout.SOUTH);

        setVisible(true);

    }

    private void setHeaderText(String text){
        headerText.setText("🐥 " + text + " 🐥");
    }

    private JPanel createHeaderPanel() {
        // Header Panel (reuse from InstagramProfileUI or customize for home page)
        // Header with the Register label
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(new Color(51, 51, 51)); // Set a darker background for the header
        headerText = new JLabel(" Explore 🐥");
        headerText.setFont(new Font("Arial", Font.BOLD, 16));
        headerText.setForeground(Color.WHITE); // Set the text color to white
        headerPanel.add(headerText);
        headerPanel.setPreferredSize(new Dimension(WIDTH, 40)); // Give the header a fixed height
        return headerPanel;
    }

    private JPanel createNavigationPanel() {
        // Navigation Bar
        JPanel navigationPanel = new JPanel();
        navigationPanel.setBackground(new Color(249, 249, 249));
        navigationPanel.setLayout(new BoxLayout(navigationPanel, BoxLayout.X_AXIS));
        navigationPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        navigationPanel.add(createIconButton("resources/imagesicons/home.png", "home"));
        navigationPanel.add(Box.createHorizontalGlue());
        navigationPanel.add(createIconButton("resources/imagesicons/search.png", "explore"));
        navigationPanel.add(Box.createHorizontalGlue());
        navigationPanel.add(createIconButton("resources/imagesicons/add.png", "add"));
        navigationPanel.add(Box.createHorizontalGlue());
        navigationPanel.add(createIconButton("resources/imagesicons/heart.png", "notification"));
        navigationPanel.add(Box.createHorizontalGlue());
        navigationPanel.add(createIconButton("resources/imagesicons/profile.png", "profile"));

        return navigationPanel;

    }

    private JButton createIconButton(String iconPath, String buttonType) {
        ImageIcon iconOriginal = new ImageIcon(iconPath);
        Image iconScaled = iconOriginal.getImage().getScaledInstance(NAV_ICON_SIZE, NAV_ICON_SIZE, Image.SCALE_SMOOTH);
        JButton button = new JButton(new ImageIcon(iconScaled));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setContentAreaFilled(false);

        // Define actions based on button type
        if ("home".equals(buttonType)) {
            button.addActionListener(e -> openHomeUI());
        } else if ("profile".equals(buttonType)) {
            button.addActionListener(e -> openProfileUI());
        } else if ("notification".equals(buttonType)) {
            button.addActionListener(e -> notificationsUI());
        } else if ("explore".equals(buttonType)) {
            button.addActionListener(e -> exploreUI());
        } else if ("add".equals(buttonType)) {
            button.addActionListener(e -> ImageUploadUI());
        }
        return button;
    }

    private void ImageUploadUI() {
        changeScreen(UI.IMAGEUPLOAD);
    }

    private void openProfileUI() {
        changeScreen(UI.PROFILE, currentUser);
    }

    private void notificationsUI() {
        changeScreen(UI.NOTIFICATIONS);
    }

    private void openHomeUI() {
        changeScreen(UI.HOME);
    }

    private void exploreUI() {
        changeScreen(UI.EXPLORE);
    }
}