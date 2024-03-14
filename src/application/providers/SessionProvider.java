package src.application.providers;

import src.domain.entities.User;

public class SessionProvider {

    private User authenticatedUser;

    private SessionProvider() {
        // Private constructor to prevent instantiation
    }

    private static final class InstanceHolder {
        private static final SessionProvider instance = new SessionProvider();
    }

    public static SessionProvider getInstance() {
        return InstanceHolder.instance;
    }

    public User getAuthenticatedUser() {
        return authenticatedUser;
    }

    public void setAuthenticatedUser(User user) {
        authenticatedUser = user;
    }

    public boolean isAuthenticated() {
        return authenticatedUser != null;
    }

    public void clearSession() {
        authenticatedUser = null;
        // Clear other session-related information
    }
}

