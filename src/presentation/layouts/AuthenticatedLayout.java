package src.presentation.layouts;

import java.awt.*;
import javax.swing.*;
import src.presentation.Router;
import src.domain.entities.User;
import src.presentation.interfaces.UIConstants;
import src.application.providers.SessionProvider;
import src.presentation.components.navigation.BottomTabsNavigation;


/**
 * This class represents the layout for authenticated users in the application.
 * It extends JPanel and is used to display the main view for authenticated users.
 * It includes a navigation bar at the bottom of the layout, which is an instance of BottomTabsNavigation.
 */
public class AuthenticatedLayout extends JPanel {

    /**
     * The router to use for navigation.
     */
    protected final Router router;

    /**
     * The session provider to use for authentication.
     */
    protected final SessionProvider sessionProvider;

    /**
     * The currently authenticated user.
     */
    protected User currentUser;

    /**
     * Creates a new AuthenticatedLayout with the specified view and router.
     * @param view The view to use for the layout.
     * @param router The router to use for navigation.
     */
    public AuthenticatedLayout(JPanel view, Router router) {
        this.router = router;
        this.sessionProvider = SessionProvider.getInstance();
        this.currentUser = sessionProvider.getAuthenticatedUser();

        // Set the layout of the panel
        setLayout(new BorderLayout());

        // Create an instance of BottomTabsNavigation
        BottomTabsNavigation bottomTabsNavigation = new BottomTabsNavigation(currentUser, router);

        // Create a JLayeredPane
        JLayeredPane layeredPane = new JLayeredPane() {
            @Override
            public void doLayout() {
                // Get the width and height of the container
                int width = getWidth();
                int height = getHeight();

                // Set the bounds of the view and bottomTabsNavigation
                view.setBounds(0, 0, width, height - bottomTabsNavigation.getPreferredSize().height);
                bottomTabsNavigation.setBounds(0, height - bottomTabsNavigation.getPreferredSize().height, width, bottomTabsNavigation.getPreferredSize().height);
            }
        };

        // Add the view and bottomTabsNavigation to the layered pane
        layeredPane.add(view, Integer.valueOf(1));
        layeredPane.add(bottomTabsNavigation, Integer.valueOf(2));

        // Add the layered pane to this panel
        add(layeredPane, BorderLayout.CENTER);
    }
}
