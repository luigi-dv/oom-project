package src.presentation.components.pictures;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.MouseEvent;

import src.domain.entities.Picture;
import src.domain.entities.User;
import src.presentation.Router;
import src.presentation.components.profile.ProfileHeaderPanel;
import src.presentation.controllers.PictureController;
import src.presentation.interfaces.UIConstants;

/**
 * A component that displays a picture.
 */
public class PictureComponent extends JPanel {

    /**
     * The router.
     */
    protected final Router router;
    /**
     * The picture.
     */
    protected final Picture picture;
    /**
     * The picture controller.
     */
    private final PictureController pictureController;
    /**
     * The listener.
     */
    private PictureComponentListener listener;
    /**
     * The like label.
     */
    private JLabel likeLabel;

    /**
     * Creates a new PictureComponent.
     * @param router The router.
     * @param picture The picture.
     * @param initialize Whether to initialize the panel.
     */
    public PictureComponent(Router router, Picture picture, boolean initialize) {
        this.router = router;
        this.picture = picture;
        this.pictureController = new PictureController();
        if (initialize) {
            initializePanel();
        }
    }

    /**
     * Sets the listener.
     * @param listener The listener to set.
     */
    public void setListener(PictureComponentListener listener) {
        this.listener = listener;
    }

    /**
     * Initializes the panel.
     */
    private void initializePanel() {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
        itemPanel.setBackground(Color.WHITE); // Set the background color for the item panel
        itemPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        itemPanel.setAlignmentX(CENTER_ALIGNMENT);

        JButton likeButton = createLikeButton(picture);
        likeButton.addActionListener(e -> {
            handleLikeAction(picture);
        });

        JLabel nameLabel = createNameLabel(picture);
        JLabel imageLabel = createImageLabel(picture);
        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                displayImage(picture); // Call a method to switch to the image view
            }
        });
        JPanel infoPanel = createInfoPanel(picture, likeButton);
        
        itemPanel.add(nameLabel);
        itemPanel.add(imageLabel);
        itemPanel.add(infoPanel);

        add(itemPanel, BorderLayout.CENTER);
    
    }

    /**
     * Displays the image.
     * @param picture The picture to display.
     */
    protected void displayImage(Picture picture) {
        listener.displayImage(new PictureDisplayComponent(router, picture));
    }

    /**
     * Creates an image label.
     * @param picture The picture to create the label for.
     * @return The image label.
     */
    protected JLabel createImageLabel(Picture picture) {
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(javax.swing.JLabel.CENTER);
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        try {
            ImageIcon imageIcon = createCroppedImageIcon(picture);
            imageLabel.setIcon(imageIcon);
        } catch (IOException ex) {
            imageLabel.setText("Image not found");
        }
        return imageLabel;
    }

    /**
     * Handles the like action.
     * @param picture The picture to like.
     */
    protected void handleLikeAction(Picture picture) {
        if (pictureController.likePicture(picture)) {
            likeLabel.setText(picture.getLikes().size() + " likes");
        } else {
            System.out.println("Failed to like picture");
        };
    }

    /**
     * Refreshes the display image.
     * @param picture The picture to refresh the display image for.
     */
    protected void refreshDisplayImage(Picture picture) {
        
    }

    /**
     * Creates a name label.
     * @param picture The picture to create the label for.
     * @return The name label.
     */
    protected JLabel createNameLabel(Picture picture) {
        JLabel nameLabel = new JLabel(picture.getUser().getUsername());
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        return nameLabel;
    }

    /**
     * Creates a user panel.
     * @param user The user to create the panel for.
     * @return The user panel.
     */
    protected JPanel createUserPanel(User user) {
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new GridLayout(1, 2));
        ProfileHeaderPanel profileHeaderPanel = new ProfileHeaderPanel(user, router);
        userPanel.add(profileHeaderPanel, BorderLayout.NORTH);
        return userPanel;
    }

    /**
     * Creates an info panel.
     * @param picture The picture to create the panel for.
     * @param likeButton The like button.
     * @return The info panel.
     */
    protected JPanel createInfoPanel(Picture picture, JButton likeButton) {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(new JLabel(picture.getCaption())); // Description
        likeLabel = new JLabel(picture.getLikes().size() + " likes");
        infoPanel.add(likeLabel); // Likes
        infoPanel.add(likeButton);
        return infoPanel;
    }

    /**
     * Creates a cropped image icon.
     * @param picture The picture to create the icon for.
     * @return The cropped image icon.
     * @throws IOException If an error occurs while creating the icon.
     */
    protected ImageIcon createCroppedImageIcon(Picture picture) throws IOException {
        String imagePath = "resources/storage/uploaded/" + picture.getImagePath();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(imagePath).getImage()
                .getScaledInstance(UIConstants.IMAGE_SIZE + 200,UIConstants.IMAGE_SIZE + 200, Image.SCALE_SMOOTH));
        return imageIcon;
    }

    /**
     * Creates a like button.
     * @param picture The picture to create the button for.
     * @return The like button.
     */
    protected JButton createLikeButton(Picture picture) {
        JButton likeButton = new JButton("❤");
        likeButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        likeButton.setBackground(new Color(255, 90, 95));
        likeButton.setOpaque(true);
        likeButton.setBorderPainted(false);
        return likeButton;
    }
    
}
