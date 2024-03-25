package src.presentation;

import javax.swing.*;
import java.util.Map;
import java.util.HashMap;

import src.presentation.layouts.GuestLayout;
import src.presentation.views.*;
import src.application.providers.SessionProvider;
import src.presentation.layouts.AuthenticatedLayout;

/**
 * The Router class manages the switching of views within the application.
 */
public class Router {
    private final JPanel mainPanel;
    private final SessionProvider sessionProvider;
    private final Map<String, JPanel> views = new HashMap<>();
    private final String SIGNUP_VIEW_NAME = "signup";
    private final String SIGNIN_VIEW_NAME = "signin";
    private final String HOME_VIEW_NAME = "home";
    private final String EXPLORE_VIEW_NAME = "explore";
    private final String NOTIFICATIONS_VIEW_NAME = "notifications";
    private final String PROFILE_VIEW_NAME = "profile";

    /**
     * Creates a new Router with the specified main panel.
     * @param mainPanel The main panel to use for the router.
     */
    public Router(JPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.sessionProvider = SessionProvider.getInstance();
        views.put(SIGNUP_VIEW_NAME, addGuestLayout(new SignUpView(this)));
        views.put(SIGNIN_VIEW_NAME, addGuestLayout(new SignInView(this)));
    }

    /**
     * Switches the view to the specified view name.
     * @param viewName The name of the view to switch to.
     */
    public void switchTo(String viewName) {
        JPanel newPanel = getView(viewName);
        // Update the views based on the current session
        updateViews();
        if (newPanel != null) {
            mainPanel.removeAll();
            mainPanel.add(newPanel);
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }

    /**
     * Returns the view with the specified name.
     * @param viewName The name of the view to return.
     * @return The view with the specified name.
     */
    public JPanel getView(String viewName) {
        // Check if the view is already cached
        JPanel view = views.get(viewName);

        // If the view is not cached or if the session has changed, update the views
        if (view == null || sessionProvider.isAuthenticated() != isViewAuthenticated(viewName)) {
            updateViews();
            view = views.get(viewName); // Update the view reference after updating views
        }

        return view;
    }

    /**
     * Checks if the specified view is an authenticated view.
     * @param viewName The name of the view to check.
     * @return True if the view is an authenticated view, false otherwise.
     */
    private boolean isViewAuthenticated(String viewName) {
        return viewName.equals(HOME_VIEW_NAME) || viewName.equals(EXPLORE_VIEW_NAME) ||
                viewName.equals(NOTIFICATIONS_VIEW_NAME) || viewName.equals(PROFILE_VIEW_NAME);
    }


    /**
     * Adds the BottomTabsNavigation component to the bottom of the view.
     *
     * @param view The view to add the BottomTabsNavigation component to.
     * @return The view with the BottomTabsNavigation component added to the bottom.
     */
    private AuthenticatedLayout addAuthenticatedLayout(JPanel view) {
        return new AuthenticatedLayout(view, this);
    }

    /**
     * Adds the GuestLayout component to the view.
     * @param view The view to add the GuestLayout component to.
     * @return The view with the GuestLayout component added.
     */
    private GuestLayout addGuestLayout(JPanel view) {
        return new GuestLayout(view, this);
    }

    /**
     * Updates the views based on the current session.
     */
    private void updateViews() {
        if (sessionProvider.isAuthenticated()) {
            views.put(HOME_VIEW_NAME, addAuthenticatedLayout(new HomeView(this)));
            views.put(EXPLORE_VIEW_NAME, addAuthenticatedLayout(new ExploreView(this)));
            views.put(NOTIFICATIONS_VIEW_NAME, addAuthenticatedLayout(new NotificationsView(this)));
            views.put(PROFILE_VIEW_NAME, addAuthenticatedLayout(new ProfileView(this)));
        }
    }
}
