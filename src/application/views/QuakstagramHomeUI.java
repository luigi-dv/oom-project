package src.application.views;

import src.application.controllers.UIController;
import src.application.views.interfaces.UIConstants;
import src.domain.entities.Picture;
import src.domain.entities.User;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class QuakstagramHomeUI extends JPanel {
    private final int IMAGE_WIDTH = UIConstants.WIDTH - 100; // Width for the image posts
    private final int IMAGE_HEIGHT = 150; // Height for the image posts
    private final Color LIKE_BUTTON_COLOR = new Color(255, 90, 95); // Color for the like button

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JPanel homePanel;
    private JPanel imageViewPanel;
    private JLabel likesLabel;

    private UIController controller;
    private User user;

    public QuakstagramHomeUI(GUI gui, User user) {
        this.user = user;
        controller = new UIController();
        
        setSize(UIConstants.WIDTH, UIConstants.HEIGHT);
        setMinimumSize(new Dimension(UIConstants.WIDTH, UIConstants.HEIGHT));
        setLayout(new BorderLayout());

        homePanel = new JPanel(new BorderLayout());
        imageViewPanel = new JPanel(new BorderLayout());
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        initializeUI();

        cardPanel.add(homePanel, "Home");
        cardPanel.add(imageViewPanel, "ImageView");
        add(cardPanel, BorderLayout.CENTER);
        cardLayout.show(cardPanel, "Home");
    }

    private void initializeUI() {

        // Content Scroll Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS)); // Vertical box layout

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); // Never allow

        // horizontal scrolling
        List<Picture> pictures = controller.getAllPictures();
        populateContentPanel(contentPanel, pictures);
        add(scrollPane, BorderLayout.CENTER);

        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        homePanel.add(scrollPane, BorderLayout.CENTER);

    }

    private void populateContentPanel(JPanel panel, List<Picture> pictures) {

        for (Picture picture : pictures) {
            JPanel itemPanel = createItemPanel(picture);
            JPanel spacingPanel = createSpacingPanel();
            panel.add(itemPanel);
            panel.add(spacingPanel);
        }
    }

    private void handleLikeAction(Picture picture) {
        
        if (controller.likePicture(picture)){
            likesLabel.setText(picture.getLikes().size() + " likes");
        }      
    }

    private JPanel createSpacingPanel() {
        JPanel spacingPanel = new JPanel();
        spacingPanel.setPreferredSize(new Dimension(UIConstants.WIDTH - 10, 5)); // Set the height for spacing
        spacingPanel.setBackground(new Color(230, 230, 230));
        return spacingPanel;
    }

    private JButton createLikeButton(Picture picture) {
        JButton likeButton = new JButton("❤");
        likeButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        likeButton.setBackground(LIKE_BUTTON_COLOR);
        likeButton.setOpaque(true);
        likeButton.setBorderPainted(false);
        return likeButton;
    }

    private JLabel createLikesLabel(Picture picture) {
        JLabel likesLabel = new JLabel(picture.getLikes().size() + " likes");
        likesLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        return likesLabel;
    }

    private JLabel getDescriptionLabel(Picture picture) {
        JLabel descriptionLabel = new JLabel(picture.getCaption());
        descriptionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        return descriptionLabel;
    }

    private JLabel createImageLabel(Picture picture) {
        JLabel imageLabel = new JLabel();
        imageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        imageLabel.setPreferredSize(new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT));
        imageLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Add border to image label
        try {
            ImageIcon imageIcon = createCroppedImageIcon(picture);
            imageLabel.setIcon(imageIcon);
        } catch (IOException ex) {
            // Handle exception: Image file not found or reading error
            imageLabel.setText("Image not found");
        }
        imageLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                displayImage(picture); // Call a method to switch to the image view
            }
        });
        return imageLabel;
    }

    private ImageIcon createCroppedImageIcon(Picture picture) throws IOException {
        String imagePath = "resources/storage/uploaded/" + picture.getImagePath();
        BufferedImage originalImage = ImageIO.read(new File(imagePath));
        BufferedImage croppedImage = originalImage.getSubimage(0, 0,
                Math.min(originalImage.getWidth(), IMAGE_WIDTH),
                Math.min(originalImage.getHeight(), IMAGE_HEIGHT));
        return new ImageIcon(croppedImage);
    }

    private JLabel createNameLabel(Picture picture) {
        JLabel nameLabel = new JLabel(picture.getUser().getUsername());
        nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        return nameLabel;
    }

    private JPanel createItemPanel(Picture picture) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
        itemPanel.setBackground(Color.WHITE); // Set the background color for the item panel
        itemPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        itemPanel.setAlignmentX(CENTER_ALIGNMENT);

        JLabel nameLabel = createNameLabel(picture);

        // Crop the image to the fixed size
        JLabel imageLabel = createImageLabel(picture);
        JLabel descriptionLabel = getDescriptionLabel(picture);
        likesLabel = createLikesLabel(picture);
        JButton likeButton = createLikeButton(picture);
        likeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLikeAction(picture);
            }
        });

        itemPanel.add(nameLabel);
        itemPanel.add(imageLabel);
        itemPanel.add(descriptionLabel);
        itemPanel.add(likesLabel);
        itemPanel.add(likeButton);

        return itemPanel;
    }

    private void displayImage(Picture picture) {
        imageViewPanel.removeAll(); // Clear previous content
        // Display the image
        JLabel fullSizeImageLabel = new JLabel();
        fullSizeImageLabel.setHorizontalAlignment(JLabel.CENTER);

        try {
            ImageIcon imageIcon = createCroppedImageIcon(picture);
            fullSizeImageLabel.setIcon(imageIcon);
        } catch (IOException ex) {
            fullSizeImageLabel.setText("Image not found");
        }

        JPanel userPanel = createUserPanel();
        JButton likeButton = createLikeButton(picture);
        likeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLikeAction(picture); // Update this line
                refreshDisplayImage(picture); // Refresh the view
            }
        });

        // Information panel at the bottom
        JPanel infoPanel = createInfoPanel(picture, likeButton);
        imageViewPanel.add(fullSizeImageLabel, BorderLayout.CENTER);
        imageViewPanel.add(infoPanel, BorderLayout.SOUTH);
        imageViewPanel.add(userPanel, BorderLayout.NORTH);
        imageViewPanel.revalidate();
        imageViewPanel.repaint();
        cardLayout.show(cardPanel, "ImageView"); // Switch to the image view
    }

    private JPanel createInfoPanel(Picture picture, JButton likeButton) {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(new JLabel(picture.getCaption())); // Description
        infoPanel.add(new JLabel(picture.getLikes().size() + "")); // Likes
        infoPanel.add(likeButton);
        return infoPanel;
    }

    private JPanel createUserPanel() {
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
        JLabel userName = new JLabel(this.user.getUsername());
        userName.setFont(new Font("Arial", Font.BOLD, 18));
        userPanel.add(userName);// User Name
        return userPanel;
    }

    private void refreshDisplayImage(Picture picture) {
        displayImage(picture);
    }

}
