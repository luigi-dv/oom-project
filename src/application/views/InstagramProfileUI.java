package src.application.views;

import java.awt.*;
import javax.swing.*;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.stream.Stream;
import src.domain.entities.User;
import src.domain.valueobjects.image.RoundedBorder;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;


/**
 * Instagram Profile UI
 */
public class InstagramProfileUI extends JPanel implements IProfile {

    private final int WIDTH;
    private final int HEIGHT;
    private final GUI GUI;
    private final int GRID_IMAGE_SIZE; // Static size for grid images

    private JPanel contentPanel; // Panel to display the image grid or the clicked image
    private JPanel headerPanel; // Panel for the header
    private User currentUser; // User object to store the current user's information

    public InstagramProfileUI(int width, int height, GUI gui, User user) {
        this.currentUser = user;
        // Initialize the user profile
        gui.controller.initializeProfile(user);
        WIDTH = width;
        HEIGHT = height;
        GUI = gui;
        GRID_IMAGE_SIZE = WIDTH / 3;
        // Initialize counts
        int imageCount = 0;
        int followersCount = 0;
        int followingCount = 0;

        // // Step 1: Read image_details.txt to count the number of images posted by the
        // // user
        // Path imageDetailsFilePath = Paths.get("img", "image_details.txt");
        // try (BufferedReader imageDetailsReader = Files.newBufferedReader(imageDetailsFilePath)) {
        //     String line;
        //     while ((line = imageDetailsReader.readLine()) != null) {
        //         if (line.contains("Username: " + currentUser.getUsername())) {
        //             imageCount++;
        //         }
        //     }
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        // Step 2: Read following.txt to calculate followers and following
        // Path followingFilePath = Paths.get("data", "following.txt");
        // try (BufferedReader followingReader = Files.newBufferedReader(followingFilePath)) {
        //     String line;
        //     while ((line = followingReader.readLine()) != null) {
        //         String[] parts = line.split(":");
        //         if (parts.length == 2) {
        //             String username = parts[0].trim();
        //             String[] followingUsers = parts[1].split(";");
        //             if (username.equals(currentUser.getUsername())) {
        //                 followingCount = followingUsers.length;
        //             } else {
        //                 for (String followingUser : followingUsers) {
        //                     if (followingUser.trim().equals(currentUser.getUsername())) {
        //                         followersCount++;
        //                     }
        //                 }
        //             }
        //         }
        //     }
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }

        // System.out.println("Bio for " + currentUser.getUsername() + ": " + bio);
        // currentUser.setBio(bio);

        // System.out.println(currentUser.getProfile().getPostsCount());

        setSize(WIDTH, HEIGHT);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new BorderLayout());
        contentPanel = new JPanel();
        headerPanel = createHeaderPanel(); // Initialize header panel
        initializeUI();
    }

    public InstagramProfileUI(int width, int height, GUI gui) {
        WIDTH = width;
        HEIGHT = height;
        GUI = gui;
        GRID_IMAGE_SIZE = WIDTH / 3;
        setSize(WIDTH, HEIGHT);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new BorderLayout());
        contentPanel = new JPanel();
        headerPanel = createHeaderPanel(); // Initialize header panel
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
        ImageIcon profileIcon = new ImageIcon(new ImageIcon("resources/storage/images/" + currentUser.getUsername() + ".png")
                .getImage().getScaledInstance(PROFILE_IMAGE_SIZE, PROFILE_IMAGE_SIZE, Image.SCALE_SMOOTH));
        JLabel profileImage = new JLabel(profileIcon);
        profileImage.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topHeaderPanel.add(profileImage, BorderLayout.WEST);

        // Stats Panel
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

    // private void handleFollowAction(String usernameToFollow) {
    //     Path followingFilePath = Paths.get("data", "following.txt");
    //     Path usersFilePath = Paths.get("data", "users.txt");
    //     String currentUserUsername = "";

    //     try {
    //         // Read the current user's username from users.txt
    //         try (BufferedReader reader = Files.newBufferedReader(usersFilePath)) {
    //             String line;
    //             while ((line = reader.readLine()) != null) {
    //                 String[] parts = line.split(":");
    //                 currentUserUsername = parts[0];
    //             }
    //         }

    //         System.out.println("Real user is " + currentUserUsername);
    //         // If currentUserUsername is not empty, process following.txt
    //         if (!currentUserUsername.isEmpty()) {
    //             boolean found = false;
    //             StringBuilder newContent = new StringBuilder();

    //             // Read and process following.txt
    //             if (Files.exists(followingFilePath)) {
    //                 try (BufferedReader reader = Files.newBufferedReader(followingFilePath)) {
    //                     String line;
    //                     while ((line = reader.readLine()) != null) {
    //                         String[] parts = line.split(":");
    //                         if (parts[0].trim().equals(currentUserUsername)) {
    //                             found = true;
    //                             if (!line.contains(usernameToFollow)) {
    //                                 line = line.concat(line.endsWith(":") ? "" : "; ").concat(usernameToFollow);
    //                             }
    //                         }
    //                         newContent.append(line).append("\n");
    //                     }
    //                 }
    //             }

    //             // If the current user was not found in following.txt, add them
    //             if (!found) {
    //                 newContent.append(currentUserUsername).append(": ").append(usernameToFollow).append("\n");
    //             }

    //             // Write the updated content back to following.txt
    //             try (BufferedWriter writer = Files.newBufferedWriter(followingFilePath)) {
    //                 writer.write(newContent.toString());
    //             }
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }



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

