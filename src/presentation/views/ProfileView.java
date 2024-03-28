package src.presentation.views;

import java.awt.*;

import src.domain.entities.User;
import src.presentation.Router;
import src.presentation.controllers.profile.ProfileController;
import src.presentation.interfaces.UIConstants;
import src.presentation.components.profile.PostGridPanel;
import src.presentation.components.profile.ProfileHeaderPanel;

import javax.swing.*;

/**
 * Instagram Profile UI
 */
public class ProfileView extends JPanel {

    private final ProfileController controller;
    private final Router router;
    private final User user;
    /**
     * Creates the profile view for the GUI.
     * @param router The router
     */
    public ProfileView(Router router) {
        this.controller = new ProfileController();
        this.router = router;
        this.user = controller.getAuthenticatedUser();
        load();
    }

    public ProfileView(Router router, User user) {
        this.controller = new ProfileController();
        this.router = router;
        this.user = user;
        load();
    }

    private void load() {
        setSize(UIConstants.WIDTH, UIConstants.HEIGHT);
        setMinimumSize(new Dimension(UIConstants.WIDTH, UIConstants.HEIGHT));
        setLayout(new BorderLayout());
        createComponent();
    }

    public void createComponent() {
        // Create an instance of ProfileHeaderPanel
        ProfileHeaderPanel profileHeaderPanel = new ProfileHeaderPanel(controller.getAuthenticatedUser(), router);
        add(profileHeaderPanel, BorderLayout.NORTH);
        // Create an instance of PostGrid
        PostGridPanel postGridPanel = new PostGridPanel(router, user);
        JScrollPane scrollPane = new JScrollPane(postGridPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null); // Remove border from scroll pane
        add(scrollPane);
    }
}

