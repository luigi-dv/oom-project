package src.presentation.components.navigation;

import src.domain.entities.User;
import src.presentation.Router;
import src.presentation.components.buttons.NavigationIconButton;

import javax.swing.*;
import java.awt.*;

public class BottomTabsNavigation extends JPanel {

    private final User currentUser;
    private final Router router;

    /**
     * Creates the navigation panel for the GUI.
     * It includes JButtons with icons for different navigation options (Home, Explore, Add, Notifications, Profile).
     */
    public BottomTabsNavigation(User currentUser, Router router) {
        this.currentUser = currentUser;
        this.router = router;
        setBackground(new Color(249, 249, 249));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(new NavigationIconButton("resources/images/icons/home.png", "home", router));
        add(Box.createHorizontalGlue());
        add(new NavigationIconButton("resources/images/icons/search.png", "explore", router));
        add(Box.createHorizontalGlue());
        add(new NavigationIconButton("resources/images/icons/add.png", "add", router));
        add(Box.createHorizontalGlue());
        if (currentUser != null) {
            add(new NavigationIconButton(currentUser.getProfilePicturePath(), "profile", router));
        } else {
            add(new NavigationIconButton("resources/images/icons/profile.png", "profile", router));
        }
    }
}
