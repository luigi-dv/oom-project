package src.presentation.components.profile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import src.domain.entities.Picture;
import src.domain.entities.User;
import src.domain.valueobjects.image.RoundedBorder;
import src.presentation.Router;
import src.presentation.controllers.profile.PostGridController;
import src.presentation.interfaces.UIConstants;

public class PostGridPanel extends JPanel {

    private final Router router;
    private final PostGridController controller;
    private final int GRID_IMAGE_SIZE = UIConstants.WIDTH / 3;
    private List<JLabel> photoLabels; // each JLabel could display an ImageIcon

    public PostGridPanel(Router router) {
        this.router = router;
        this.controller = new PostGridController();
        initializePanel();
    }

    private void initializePanel() {
        removeAll(); // Clear existing content
        setLayout(new BorderLayout()); // Change layout to BorderLayout
        User currentUser = controller.getAuthenticatedUser();
        JPanel gridPanel = new JPanel(new GridLayout(0, 3, 5, 5)); // Grid layout for image grid
        addPicturesToPanel(currentUser, gridPanel);
        JScrollPane scrollPane = new JScrollPane(gridPanel);
        add(scrollPane, BorderLayout.CENTER); // Add the scroll pane to the center
        revalidate();
        repaint();
    }

    private void addPicturesToPanel(User currentUser, JPanel gridPanel) {
        currentUser.getProfile().getPictures().forEach(picture -> {
            JLabel roundedImageLabel = createImageLabel(picture);
            gridPanel.add(roundedImageLabel);
        });
    }

    private JLabel createImageLabel(Picture picture) {
        String filePath = "resources/storage/uploaded/" + picture.getImagePath();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(filePath).getImage()
                .getScaledInstance(GRID_IMAGE_SIZE, GRID_IMAGE_SIZE, Image.SCALE_SMOOTH));
        JLabel roundedImageLabel = new JLabel(imageIcon);
        roundedImageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                displayImage(imageIcon);
            }
        });
        return roundedImageLabel;
    }

    private JScrollPane createScrollPane() {
        JScrollPane scrollPane = new JScrollPane(this);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        return scrollPane;
    }

    /**
     * Displays the full-size image when a grid image is clicked
     *
     * @param imageIcon The image icon to display
     */
    private void displayImage(ImageIcon imageIcon) {
        removeAll(); // Remove existing content
        setLayout(new BorderLayout()); // Change layout for image display

        // Create a rounded JLabel for the image
        JLabel roundedImageLabel = new JLabel(imageIcon);
        roundedImageLabel.setHorizontalAlignment(JLabel.CENTER);
        roundedImageLabel.setBorder(new RoundedBorder(10)); // Adjust the corner radius as needed
        add(roundedImageLabel, BorderLayout.CENTER);

        JButton backButton = createBackButton();
        add(backButton, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }

    private JButton createBackButton() {
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            router.switchTo("profile");
            backButton.setVisible(false);
        });
        return backButton;
    }
}

