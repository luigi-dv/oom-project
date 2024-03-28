package src.presentation.views;

import src.presentation.Router;
import src.presentation.components.navigation.InteractionBarNavigation;
import src.presentation.components.pictures.PictureComponent;
import src.presentation.components.pictures.PictureComponentListener;
import src.presentation.controllers.UIController;
import src.presentation.interfaces.UIConstants;
import src.domain.entities.Picture;

import javax.swing.*;

import java.awt.*;
import java.util.List;

public class HomeView extends JPanel implements PictureComponentListener {
    
    private final CardLayout cardLayout;
    private final JPanel cardPanel;
    private final JPanel homePanel;
    private final JPanel imageViewPanel;

    private final UIController controller;

    private final Router router;

    public HomeView(Router router) {
        this.router = router;
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

        // Add InteractionBarComponent
        InteractionBarNavigation interactionBarNavigation = new InteractionBarNavigation(router);
        homePanel.add(interactionBarNavigation, BorderLayout.NORTH);
    }

    private void populateContentPanel(JPanel panel, List<Picture> pictures) {

        for (Picture picture : pictures) {
            PictureComponent itemPanel = new PictureComponent(picture, true);
            itemPanel.setListener(this);
            JPanel spacingPanel = createSpacingPanel();
            panel.add(itemPanel);
            panel.add(spacingPanel);
        }
    }

    private JPanel createSpacingPanel() {
        JPanel spacingPanel = new JPanel();
        spacingPanel.setPreferredSize(new Dimension(UIConstants.WIDTH - 10, 5)); // Set the height for spacing
        spacingPanel.setBackground(new Color(230, 230, 230));
        return spacingPanel;
    }

    @Override
    public void displayImage(JPanel picture) {
        cardLayout.show(cardPanel, "ImageView");
        imageViewPanel.add(picture, BorderLayout.CENTER);
        imageViewPanel.revalidate();
        imageViewPanel.repaint();
    }

    

    private JPanel createUserPanel(User user) {
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new GridLayout(1, 2));
        ProfileHeaderPanel profileHeaderPanel = new ProfileHeaderPanel(user, router);
        userPanel.add(profileHeaderPanel, BorderLayout.NORTH);
        return userPanel;
    }

    private JPanel createInfoPanel(Picture picture, JButton likeButton) {
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.add(new JLabel(picture.getCaption())); // Description
        infoPanel.add(new JLabel(picture.getLikes().size() + " likes")); // Likes
        infoPanel.add(likeButton);
        return infoPanel;
    }

    private void refreshDisplayImage(Picture picture) {
        displayImage(picture);
    }

}
