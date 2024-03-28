package src.presentation.components.navigation;

import src.domain.entities.User;
import src.presentation.Router;
import src.presentation.components.buttons.NavigationIconButton;
import src.presentation.interfaces.UIConstants;
import src.presentation.views.UIViews;

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
        setMaximumSize(new Dimension(UIConstants.WIDTH, 50));
        add(new NavigationIconButton("resources/images/icons/home.png", UIViews.HOME, router));
        add(Box.createHorizontalGlue());
        add(new NavigationIconButton("resources/images/icons/search.png", UIViews.EXPLORE, router));
        add(Box.createHorizontalGlue());
        add(new NavigationIconButton("resources/images/icons/add.png", UIViews.IMAGEUPLOAD, router));
        add(Box.createHorizontalGlue());
        if (currentUser != null) {
            add(new NavigationIconButton(currentUser.getProfilePicturePath(), UIViews.PROFILE, router));
        } else {
            add(new NavigationIconButton("resources/images/icons/profile.png", UIViews.PROFILE, router));
        }
    }
}
