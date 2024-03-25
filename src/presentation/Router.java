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
    private final Map<UIViews, JPanel> views = new HashMap<>();

    /**
     * Creates a new Router with the specified main panel.
     * @param mainPanel The main panel to use for the router.
     */
    public Router(JPanel mainPanel) {
        this.mainPanel = mainPanel;
        this.sessionProvider = SessionProvider.getInstance();
        views.put(UIViews.SIGNUP, addGuestLayout(new SignUpView(this)));
        views.put(UIViews.SIGNIN, addGuestLayout(new SignInView(this)));
    }

    /**
     * Switches the view to the specified view name.
     * @param viewName The name of the view to switch to.
     */
    public void switchTo(UIViews viewName) {
        
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
    public JPanel getView(UIViews viewName) {
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
    private boolean isViewAuthenticated(UIViews viewName) {
        return viewName == UIViews.HOME || viewName == UIViews.EXPLORE ||
                viewName == UIViews.NOTIFICATIONS || viewName == UIViews.PROFILE;
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
            views.put(UIViews.HOME, addAuthenticatedLayout(new HomeView(this)));
            views.put(UIViews.EXPLORE, addAuthenticatedLayout(new ExploreView(this)));
            views.put(UIViews.NOTIFICATIONS, addAuthenticatedLayout(new NotificationsView(this)));
            views.put(UIViews.PROFILE, addAuthenticatedLayout(new ProfileView(this)));
            views.put(UIViews.IMAGEUPLOAD, addAuthenticatedLayout(new ImageUploadView(this)));
        }
    }
}
