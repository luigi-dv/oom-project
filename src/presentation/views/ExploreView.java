package src.presentation.views;

import src.domain.entities.User;
import src.domain.interfaces.ISearchable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import src.presentation.components.buttons.ButtonComponent;
import src.presentation.components.pictures.PictureDisplayComponent;
import src.presentation.interfaces.IUserSearchListener;
import src.presentation.components.ui.HintTextField;

import src.presentation.Router;
import src.presentation.controllers.ExploreController;
import src.presentation.interfaces.IExploreUI;
import src.presentation.interfaces.UIConstants;
import src.domain.entities.Picture;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * The ExploreUI class represents the user interface for exploring pictures in
 * the application.
 * It includes features such as displaying a grid of images, viewing details of
 * a selected picture,
 * and navigating between different screens.
 *
 * @authors Melcher Toby, Davila Luigelo, Eliëns Joa, Nijhuis Julian
 * @version 1.0
 */
public class ExploreView extends JPanel implements IUserSearchListener {
    private final int IMAGE_SIZE = UIConstants.WIDTH / 3; // Size for each image in the grid

    private final ExploreController controller;

    private final Router router;

    /**
     * Constructor for ExploreUI.
     * @param router The router for navigating between different screens.
     */
    public ExploreView(Router router) {
        this.router = router;
        this.controller = new ExploreController();
        setSize(UIConstants.WIDTH, UIConstants.HEIGHT);
        setMinimumSize(new Dimension(UIConstants.WIDTH, UIConstants.HEIGHT));
        setLayout(new BorderLayout());
        initializeUI();
    }

    /**
     * Initializes the user interface by setting up the layout and calling the
     * necessary methods.
     */
    private void initializeUI() {
        setLayout(new BorderLayout());
        JPanel mainPanel = createMainContentPanel();
        add(mainPanel);
        revalidate();
        repaint();

    }

    /**
     * Creates the main content panel containing the search bar, image grid, and
     * scroll functionality.
     *
     * @return The main content panel.
     */
    private JPanel createMainContentPanel() {
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        List<Picture> pictures = controller.getallPictures();
        
        JPanel searchPanel = createSearchPanel();
        JPanel imageGridPanel = IExploreUI.createImageGridPanel(pictures, mouseAdapterImage, IMAGE_SIZE);
        JScrollPane scrollPane = IExploreUI.createScrollPane(imageGridPanel);

        mainContentPanel.add(searchPanel);
        mainContentPanel.add(scrollPane);
        return mainContentPanel;
    }

    private void displaySearchResults(List<ISearchable> searchResults) {
        // Clear existing content
        removeAll();
        System.out.println(searchResults.size());
        // Check the type of the first item to decide on the display strategy
        if (!searchResults.isEmpty()) {
            if (searchResults.get(0) instanceof User) {
                List<User> users = searchResults.stream().map(item -> (User) item).collect(Collectors.toList());
                JPanel userGridPanel = IExploreUI.createUserGridPanel(users, mouseAdapterUser, IMAGE_SIZE, this);
                add(userGridPanel, BorderLayout.CENTER);
            } else if (searchResults.get(0) instanceof Picture) {
                List<Picture> pictures = searchResults.stream().map(item -> (Picture) item).collect(Collectors.toList());
                JPanel imageGridPanel = IExploreUI.createImageGridPanel(pictures, mouseAdapterImage, IMAGE_SIZE);
                add(imageGridPanel, BorderLayout.CENTER);
            }
        } else {
            // Handle empty or unrecognized search results
            JLabel noResultsLabel = new JLabel("No results found");
            add(noResultsLabel, BorderLayout.CENTER);
        }
        
        // Always add back the search panel at the top
        add(createSearchPanel(), BorderLayout.NORTH);
        
        revalidate();
        repaint();
    }

    private JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new BorderLayout());

        JTextField searchField = new HintTextField("Search for Users/Caption/Hashtags...");

        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, searchField.getPreferredSize().height)); // Limit

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
            String query = searchField.getText();
            List<ISearchable> searchables = controller.search(query);
            displaySearchResults(searchables);
        });
        searchPanel.add(searchButton, BorderLayout.EAST);

        return searchPanel;
    }

    /**
     * Event handler for mouse clicks on images in the grid. Displays the details of
     * the selected picture.
     */
    MouseAdapter mouseAdapterImage = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            JLabel clickedLabel = (JLabel) e.getSource();
            String pictureId = clickedLabel.getName();
            UUID id = UUID.fromString(pictureId);
            Picture picture = controller.getPictureById(id);
            displayImage(picture);
        }
    };

    MouseAdapter mouseAdapterUser = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            // router.switchTo(UIViews.PROFILE, user);
        }
    };

    @Override
    public void displayUserDetails(JPanel picture, User user) {
        JPanel panel = new ProfileView(router, user);
        removeAll();
        add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    /**
     * Creates a panel with a "Back" button for navigating back to the main content
     * panel.
     *
     * @return The panel containing the "Back" button.
     */
    private JButton createBackButtonPanel() {
        JButton backButton = new ButtonComponent("Back", 14, 5, Component.CENTER_ALIGNMENT, "secondary", false);

        // Make the button take up the full width
        backButton.setPreferredSize(new Dimension(WIDTH - 20, backButton.getPreferredSize().height));
        backButton.addActionListener(e -> {
            removeAll();
            add(createMainContentPanel(), BorderLayout.CENTER);
            revalidate();
            repaint();
        });
        return backButton;
    }

    /**
     * Displays the details of the selected picture, including the image, username,
     * posting time, caption, and likes.
     *
     * @param picture The selected picture.
     */
    private void displayImage(Picture picture) {
        removeAll();
        setLayout(new BorderLayout());
        JPanel panel = new PictureDisplayComponent(router, picture);
        JButton backPanel = createBackButtonPanel();
        add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    
}
