package src.application.views;

import src.application.controllers.UIController;
import src.domain.entities.User;
import src.infrastructure.utilities.Crypter;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * The GUI class represents the main graphical user interface for the Quackstagram application.
 * It extends JFrame to create the main window for the application.
 *
 * @authors Melcher Toby, Davila Luigelo, Eliëns Joa, Nijhuis Julian
 * @version 1.0
 */
public class GUI extends JFrame {

    public Crypter crypter;

    public User currentUser;

    private static final int WIDTH = 300;
    private static final int HEIGHT = 500;
    private static final int NAV_ICON_SIZE = 20; // Corrected static size for bottom icons
    private JPanel navigationPanel; // Panel for the navigation
    private JPanel headerPanel;
    private JLabel headerText;
    protected final UIController controller;

    /**
     * Main method to launch the Quackstagram application.
     * It instantiates an object of the GUI class and sets it as visible.
     * The initial screen is determined based on the user's authentication status.
     *
     * @param args Command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GUI frame = new GUI();
            frame.setVisible(true);
        });
    }

    /**
     * Constructor for the GUI class.
     * It initializes the UIController, sets up user authentication and session,
     * and creates the main window with an initial screen (SignInUI or SignUpUI).
     * The navigation and header panels are also initialized.
     */
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

    public void changeAuthenticationScreen(UI panel) {
        getContentPane().removeAll();
        repaint();
        JPanel pane = new JPanel();
        switch (panel) {
            case SIGNIN:
                setTitle("Quackstagram - Sign In");
                pane = new SignInUI(WIDTH, HEIGHT, this);
                break;
            case SIGNUP:
                setTitle("Quackstagram - Register");
                pane = new SignUpUI(WIDTH, HEIGHT, this);
                break;
            default:
                break;
        }
        add(pane, BorderLayout.CENTER);
        setVisible(true);
    }

    /**
     * Changes the main screen based on the provided UI panel type.
     * It updates the title, removes all components, repaints the UI, and adds the specified panel.
     * Additionally, it manages the header and navigation panels based on the selected screen.
     *
     * @param panel The type of main screen (Explore, Profile, Notifications, Home, Image Upload)
     */
    public void changeScreen(UI panel){
        if (controller.getAuthenticatedUser() == null) {
            changeAuthenticationScreen(UI.SIGNIN);
        } else {
            currentUser = controller.getAuthenticatedUser();
            getContentPane().removeAll();
            repaint();
            JPanel pane = new JPanel();
            boolean header = false;
            boolean footer = false;
            switch (panel) {
                case EXPLORE:
                    setTitle("Explore");
                    pane = new ExploreUI(WIDTH, HEIGHT, this, currentUser);
                    setHeaderText("Explore");
                    footer = true;
                    header = true;
                    break;
                case PROFILE:
                    setTitle("@" + currentUser.getUsername());
                    pane = new InstagramProfileUI(WIDTH, HEIGHT, this, currentUser);
                    footer = true;
                    break;
                case IMAGEUPLOAD:
                    setTitle("Upload Image");
                    pane = new ImageUploadUI(WIDTH, HEIGHT, this, currentUser);
                    setHeaderText("Upload Image");
                    header = true;
                    footer = true;
                    break;
                case NOTIFICATIONS:
                    setTitle("Notifications");
                    pane = new NotificationsUI(WIDTH, HEIGHT, this, currentUser);
                    setHeaderText("Notifications");
                    header = true;
                    footer = true;
                    break;
                case HOME:
                    setTitle("Quakstagram Home");
                    pane = new QuakstagramHomeUI(WIDTH, HEIGHT, this, currentUser);
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
    }

    /**
     * Sets the header text based on the provided text.
     * It updates the text of the header panel to display the current screen.
     *
     * @param text The text to be displayed in the header panel
     */
    private void setHeaderText(String text){
        headerText.setText("🐥 " + text + " 🐥");
    }

    /**
     * Creates the header panel for the GUI.
     * It includes a JLabel for displaying the screen text and sets the panel's appearance.
     *
     * @return The created header panel
     */
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

    /**
     * Creates the navigation panel for the GUI.
     * It includes JButtons with icons for different navigation options (Home, Explore, Add, Notifications, Profile).
     *
     * @return The created navigation panel
     */
    private JPanel createNavigationPanel() {
        // Navigation Bar
        JPanel navigationPanel = new JPanel();
        navigationPanel.setBackground(new Color(249, 249, 249));
        navigationPanel.setLayout(new BoxLayout(navigationPanel, BoxLayout.X_AXIS));
        navigationPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        navigationPanel.add(createIconButton("resources/images/icons/home.png", "home"));
        navigationPanel.add(Box.createHorizontalGlue());
        navigationPanel.add(createIconButton("resources/images/icons/search.png", "explore"));
        navigationPanel.add(Box.createHorizontalGlue());
        navigationPanel.add(createIconButton("resources/images/icons/add.png", "add"));
        navigationPanel.add(Box.createHorizontalGlue());
        navigationPanel.add(createIconButton("resources/images/icons/heart.png", "notification"));
        navigationPanel.add(Box.createHorizontalGlue());
        navigationPanel.add(createIconButton("resources/images/icons/profile.png", "profile"));

        return navigationPanel;

    }

    /**
     * Creates an icon button with the specified icon path and button type.
     * It scales the icon, creates a button, and sets action listeners based on the button type.
     *
     * @param iconPath  The path of the icon image
     * @param buttonType The type of button (home, explore, add, notification, profile)
     * @return The created icon button
     */
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

    /**
     * Opens the Image Upload UI screen.
     * It triggers a screen transition to the ImageUploadUI.
     */
    private void ImageUploadUI() {
        changeScreen(UI.IMAGEUPLOAD);
    }

    /**
     * Opens the Profile UI screen.
     * It triggers a screen transition to the InstagramProfileUI.
     */
    private void openProfileUI() {
        changeScreen(UI.PROFILE);
    }

    /**
     * Opens the Notifications UI screen.
     * It triggers a screen transition to the NotificationsUI.
     */
    private void notificationsUI() {
        changeScreen(UI.NOTIFICATIONS);
    }

    /**
     * Opens the Home UI screen.
     * It triggers a screen transition to the QuakstagramHomeUI.
     */
    private void openHomeUI() {
        changeScreen(UI.HOME);
    }

    /**
     * Opens the Explore UI screen.
     * It triggers a screen transition to the ExploreUI.
     */
    private void exploreUI() {
        changeScreen(UI.EXPLORE);
    }
}