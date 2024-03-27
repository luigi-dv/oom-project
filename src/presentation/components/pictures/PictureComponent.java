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
import src.presentation.components.profile.ProfileHeaderPanel;
import src.presentation.controllers.PictureController;
import src.presentation.interfaces.UIConstants;

public class PictureComponent extends JPanel {

    protected final Picture picture;
    private final PictureController pictureController;
    private PictureComponentListener listener;
    private JLabel likeLabel;

    public PictureComponent(Picture picture, boolean initialize) {
        this.picture = picture;
        this.pictureController = new PictureController();
        if (initialize) {
            initializePanel();
        }
    }

    public void setListener(PictureComponentListener listener) {
        this.listener = listener;
    }

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

    protected void displayImage(Picture picture) {
        listener.displayImage(new PictureDisplayComponent(picture));   
    }

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

    protected void handleLikeAction(Picture picture) {
        if (pictureController.likePicture(picture)) {
            likeLabel.setText(picture.getLikes().size() + " likes");
        } else {
            System.out.println("Failed to like picture");
        };
    }

    protected void refreshDisplayImage(Picture picture) {
        
    }

    protected JLabel createNameLabel(Picture picture) {
        JLabel nameLabel = new JLabel(picture.getUser().getUsername());
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        return nameLabel;
    }

    protected JPanel createUserPanel(User user) {
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new GridLayout(1, 2));
        ProfileHeaderPanel profileHeaderPanel = new ProfileHeaderPanel(user);
        userPanel.add(profileHeaderPanel, BorderLayout.NORTH);
        return userPanel;
    }

    protected JPanel createInfoPanel(Picture picture, JButton likeButton) {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(new JLabel(picture.getCaption())); // Description
        likeLabel = new JLabel(picture.getLikes().size() + " likes");
        infoPanel.add(likeLabel); // Likes
        infoPanel.add(likeButton);
        return infoPanel;
    }

    protected ImageIcon createCroppedImageIcon(Picture picture) throws IOException {
        String imagePath = "resources/storage/uploaded/" + picture.getImagePath();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon(imagePath).getImage()
                .getScaledInstance(UIConstants.IMAGE_SIZE + 200,UIConstants.IMAGE_SIZE + 200, Image.SCALE_SMOOTH));
        return imageIcon;
    }

    protected JButton createLikeButton(Picture picture) {
        JButton likeButton = new JButton("❤");
        likeButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        likeButton.setBackground(new Color(255, 90, 95));
        likeButton.setOpaque(true);
        likeButton.setBorderPainted(false);
        return likeButton;
    }
    
}
