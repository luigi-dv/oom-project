package src.application.views;

import src.domain.entities.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import src.application.controllers.ExploreController;
import src.application.views.interfaces.IExploreUI;
import src.application.views.interfaces.UIConstants;
import src.domain.entities.Picture;
import java.util.List;
import java.util.UUID;

public class ExploreUI extends JPanel {

    private final GUI GUI;
    private final int IMAGE_SIZE = UIConstants.WIDTH / 3;; // Size for each image in the grid

    private final ExploreController controller;

    public ExploreUI(GUI gui, User user) {
        this.controller = new ExploreController();
        GUI = gui;
        setSize(UIConstants.WIDTH, UIConstants.HEIGHT);
        setMinimumSize(new Dimension(UIConstants.WIDTH, UIConstants.HEIGHT));
        setLayout(new BorderLayout());
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());
        JPanel mainPanel = createMainContentPanel();
        add(mainPanel);
        revalidate();
        repaint();

    }

    private JPanel createMainContentPanel() {
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        List<Picture> pictures = controller.getallPictures();
        JPanel searchPanel = IExploreUI.createSearchPanel();
        JPanel imageGridPanel = IExploreUI.createImageGridPanel(pictures, mouseAdapter, IMAGE_SIZE);
        JScrollPane scrollPane = IExploreUI.createScrollPane(imageGridPanel);

        mainContentPanel.add(searchPanel);
        mainContentPanel.add(scrollPane);
        return mainContentPanel;
    }

    MouseAdapter mouseAdapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel clickedLabel = (JLabel) e.getSource();
            String pictureId = clickedLabel.getName();
            UUID id = UUID.fromString(pictureId);
            Picture picture = controller.getPictureById(id);
            displayImage(picture);
        }
    };

    private JPanel createTopPanel(Picture picture) {
        JPanel topPanel = new JPanel(new BorderLayout());
        JButton usernameLabel = new JButton(picture.getUser().getUsername());
        JLabel timeLabel = new JLabel("Yesterday");
        timeLabel.setHorizontalAlignment(JLabel.RIGHT);

        usernameLabel.addActionListener(e -> {
            // TODO: Open the correct profile
            GUI.changeScreen(UI.PROFILE);
        });

        topPanel.add(usernameLabel, BorderLayout.WEST);
        topPanel.add(timeLabel, BorderLayout.EAST);
        return topPanel;
    }

    private JPanel createBottomPanel(Picture picture) {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        JTextArea bioTextArea = new JTextArea(picture.getCaption());
        bioTextArea.setEditable(false);
        JLabel likesLabel = new JLabel("Likes: " + picture.getLikes().size());
        bottomPanel.add(bioTextArea, BorderLayout.CENTER);
        bottomPanel.add(likesLabel, BorderLayout.SOUTH);
        return bottomPanel;
    }

    private JLabel createImageLabel(Picture picture) {
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        try {
            BufferedImage originalImage = ImageIO.read(new File(picture.getImagePath()));
            ImageIcon imageIcon = new ImageIcon(originalImage);
            imageLabel.setIcon(imageIcon);
        } catch (IOException ex) {
            imageLabel.setText("Image not found");
        }
        return imageLabel;
    } 

    private JPanel createBackButtonPanel() {
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton("Back");

        // Make the button take up the full width
        backButton.setPreferredSize(new Dimension(WIDTH - 20, backButton.getPreferredSize().height));
        backButtonPanel.add(backButton);
        backButton.addActionListener(e -> {
            add(createMainContentPanel(), BorderLayout.CENTER);
            revalidate();
            repaint();
        });
        return backButtonPanel;
    }

    private void displayImage(Picture picture) {
        setLayout(new BorderLayout());

 
        // TODO: get likes count
        // TODO: Calculate time since posting
    

        // Top panel for username and time since posting
        JPanel topPanel = createTopPanel(picture);
        // Prepare the image for display
        JLabel imageLabel = createImageLabel(picture);
        // Bottom panel for bio and likes
        JPanel bottomPanel = createBottomPanel(picture);

        // Adding the components to the frame
        add(topPanel, BorderLayout.NORTH);
        add(imageLabel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        // Panel for the back button
        JPanel backButtonPanel = createBackButtonPanel();
       
        // Container panel for image and details
        JPanel containerPanel = new JPanel(new BorderLayout());
        containerPanel.add(topPanel, BorderLayout.NORTH);
        containerPanel.add(imageLabel, BorderLayout.CENTER);
        containerPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Add the container panel and back button panel to the frame
        add(backButtonPanel, BorderLayout.NORTH);
        add(containerPanel, BorderLayout.CENTER);

        revalidate();
        repaint();
    }
}


