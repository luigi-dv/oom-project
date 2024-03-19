package src.presentation.views;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;

import src.infrastructure.utilities.Crypter;
import src.presentation.Router;

public class Main extends JFrame {

    public Crypter crypter; // Crypter object for encryption and decryption
    private final JPanel mainPanel;
    private final CardLayout cardLayout;

    private final Router router;

    /**
     * Creates the main frame for the Quackstagram application.
     */
    public Main() {
        initEncryption();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 500);
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        router = new Router(mainPanel);
        add(mainPanel, BorderLayout.CENTER);
        router.switchTo("signin");
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
            Main frame = new Main();
            frame.setVisible(true);
        });
    }

}
