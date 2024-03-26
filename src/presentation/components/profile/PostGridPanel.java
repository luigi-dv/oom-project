package src.presentation.components.profile;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import src.domain.entities.Picture;
import src.domain.entities.User;
import src.presentation.Router;
import src.presentation.components.buttons.ButtonComponent;
import src.presentation.controllers.profile.PostGridController;

public class PostGridPanel extends JPanel {

    private final Router router;
    private final PostGridController controller;
    private int GRID_IMAGE_SIZE = 100; // Adjust as needed

    public PostGridPanel(Router router) {
        this.router = router;
        this.controller = new PostGridController();
        initializePanel();
    }

    private void initializePanel() {
        removeAll(); // Clear existing content
        int numImagesPerRow = 3;
        int gap = 5; // Gap between images
        setLayout(new GridLayout(0, numImagesPerRow, gap, gap)); // GridLayout for the grid with numImagesPerRow columns
        setBackground(Color.WHITE); // Set background color to white

        User currentUser = controller.getAuthenticatedUser();
        addPicturesToPanel(currentUser);

        revalidate();
        repaint();
    }

    private void addPicturesToPanel(User currentUser) {
        List<Picture> pictures = currentUser.getProfile().getPictures();

        for (Picture picture : pictures) {
            JLabel roundedImageLabel = createImageLabel(picture);
            add(roundedImageLabel);
        }
    }

    private JLabel createImageLabel(Picture picture) {
        
        ImageIcon imageIcon = createImageIcon(picture);
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBorder(new LineBorder(Color.BLACK)); // Set border color to black
        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                displayImage(picture);
            }
        });
        return imageLabel;
    }

    private ImageIcon createImageIcon(Picture picture) {
        String filePath = "resources/storage/uploaded/" + picture.getImagePath();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(filePath).getImage()
                .getScaledInstance(GRID_IMAGE_SIZE, GRID_IMAGE_SIZE, Image.SCALE_SMOOTH));
        return imageIcon;
    }

    /**
     * Displays the full-size image when a grid image is clicked
     *
     * @param imageIcon The image icon to display
     */
    private void displayImage(Picture picture) {
        removeAll(); // Remove existing content
        setLayout(new BorderLayout()); // Change layout for image display
        ImageIcon imageIcon = createImageIcon(picture);
        JButton backButton = createBackButton();
        // create delete button to delete the picture
        ButtonComponent deleteButton = new ButtonComponent("Delete", 14, 10, Component.CENTER_ALIGNMENT, "danger", true);
        deleteButton.setMaximumSize(new Dimension(30, 30));
        deleteButton.addActionListener(e -> {
            controller.deletePicture(picture);
            backButton.doClick();
            revalidate();
            repaint();
        });
        add(deleteButton, BorderLayout.NORTH);
        // Create a JLabel for the image
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        add(imageLabel, BorderLayout.CENTER);

        add(backButton, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }

    private JButton createBackButton() {
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            initializePanel();
            backButton.setVisible(false);
        });
        return backButton;
    }
}