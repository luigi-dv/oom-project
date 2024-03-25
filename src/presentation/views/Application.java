package src.presentation.views;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;

import src.infrastructure.utilities.Crypter;
import src.presentation.Router;

/**
 * Class to launch the Quackstagram application.
 */
public class Application extends JFrame {

    public Crypter crypter;

    /**
     * Creates the main frame for the Quackstagram application.
     */
    public Application() {
        initEncryption();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 500);
        // Set the window title
        setTitle("Quackstagram");
        // Set the window layout
        CardLayout cardLayout = new CardLayout();
        // Create the main panel with a card layout
        JPanel mainPanel = new JPanel(cardLayout);
        // Initialize the router
        Router router = new Router(mainPanel);
        add(mainPanel, BorderLayout.CENTER);
        router.switchTo(UIViews.SIGNIN);
    }

    /**
     * Initializes the encryption and decryption functionality.
     */
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

    /**
     * Main method to launch the Quackstagram application.
     *
     * @param args Command-line arguments (not used in this application)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Application frame = new Application();
            frame.setVisible(true);
        });
    }

}
