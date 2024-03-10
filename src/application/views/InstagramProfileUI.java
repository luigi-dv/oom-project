package src.application.views;

import java.awt.*;
import javax.swing.*;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.stream.Stream;

import src.application.views.interfaces.IProfile;
import src.application.views.interfaces.UIConstants;
import src.domain.entities.User;
import src.domain.valueobjects.image.RoundedBorder;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;


/**
 * Instagram Profile UI
 */
public class InstagramProfileUI extends JPanel implements IProfile {

    private final int GRID_IMAGE_SIZE; // Static size for grid images

    private JPanel contentPanel; // Panel to display the image grid or the clicked image
    private JPanel headerPanel; // Panel for the header
    private User currentUser; // User object to store the current user's information

    public InstagramProfileUI(GUI gui, User user) {
        this.currentUser = user;
        // Initialize the user profile
        gui.controller.initializeProfile(user);
        GRID_IMAGE_SIZE = UIConstants.WIDTH / 3;

        setSize(UIConstants.WIDTH, UIConstants.HEIGHT);
        setMinimumSize(new Dimension(UIConstants.WIDTH, UIConstants.HEIGHT));
        setLayout(new BorderLayout());
        contentPanel = new JPanel();
        headerPanel = createHeaderPanel();
        initializeUI();
    }

    private void initializeUI() {
        add(headerPanel, BorderLayout.NORTH);
        initializeImageGrid();
        revalidate();
        repaint();
    }

    /**
     * Create the header panel: This panel contains the profile image, stats, and the follow button
     * if the profile is not the current user's.
     *
     * @return JPanel
     */
    private JPanel createHeaderPanel() {

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(Color.GRAY);

        // Top Part of the Header (Profile Image, Stats, Follow Button)
        JPanel topHeaderPanel = new JPanel(new BorderLayout(10, 0));
        topHeaderPanel.setBackground(new Color(249, 249, 249));

        // Profile image
        JLabel  profileImage  = IProfile.createProfileImage(currentUser);
        topHeaderPanel.add(profileImage, BorderLayout.WEST);

        // Stats Panel - this can be put into the IProfile interface
        JPanel statsPanel = IProfile.createStatsPanel();
        JPanel statsFollowPanel = IProfile.createStatsFollowPanel(currentUser);
        JButton editProfileButton = IProfile.createEditProfileButton();
        statsPanel.add(statsFollowPanel);
        statsPanel.add(editProfileButton);

        topHeaderPanel.add(statsPanel, BorderLayout.CENTER);
        headerPanel.add(topHeaderPanel);

        // Profile Name and Bio Panel
        JPanel profileNameAndBioPanel = IProfile.createProfileNameAndBioPanel(currentUser);
        headerPanel.add(profileNameAndBioPanel);

        return headerPanel;

    }


    private void initializeImageGrid() {
        contentPanel.removeAll(); // Clear existing content
        contentPanel.setLayout(new GridLayout(0, 3, 5, 5)); // Grid layout for image grid

        Path imageDir = Paths.get("resources/images");
        try (Stream<Path> paths = Files.list(imageDir)) {
            paths.filter(path -> path.getFileName().toString().startsWith(currentUser.getUsername() + "_"))
                    .forEach(path -> {
                        ImageIcon imageIcon = new ImageIcon(new ImageIcon(path.toString()).getImage()
                                .getScaledInstance(GRID_IMAGE_SIZE, GRID_IMAGE_SIZE, Image.SCALE_SMOOTH));

                        // Wrap the imageIcon in a rounded JLabel
                        JLabel roundedImageLabel = new JLabel(imageIcon);
                        roundedImageLabel.setHorizontalAlignment(JLabel.CENTER);
                        roundedImageLabel.setBorder(new RoundedBorder(10)); // Adjust the corner radius as needed

                        roundedImageLabel.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                displayImage(imageIcon); // Call method to display the clicked image
                            }
                        });

                        contentPanel.add(roundedImageLabel);
                    });
        } catch (IOException ex) {
            ex.printStackTrace();
            // Handle exception (e.g., show a message or log)
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(scrollPane, BorderLayout.CENTER); // Add the scroll pane to the center

        revalidate();
        repaint();
    }


    /**
     * Displays the full-size image when a grid image is clicked
     *
     * @param imageIcon The image icon to display
     */
    private void displayImage(ImageIcon imageIcon) {
        contentPanel.removeAll(); // Remove existing content
        contentPanel.setLayout(new BorderLayout()); // Change layout for image display

        // Create a rounded JLabel for the image
        JLabel roundedImageLabel = new JLabel(imageIcon);
        roundedImageLabel.setHorizontalAlignment(JLabel.CENTER);
        roundedImageLabel.setBorder(new RoundedBorder(10)); // Adjust the corner radius as needed
        contentPanel.add(roundedImageLabel, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            initializeUI(); // Re-initialize the UI
        });
        contentPanel.add(backButton, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }

}

