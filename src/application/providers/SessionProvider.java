package src.application.providers;

import src.domain.entities.User;

/**
 * Singleton class responsible for managing user sessions.
 * This class provides functionality to authenticate users, retrieve authenticated users, check authentication status, and clear sessions.
 */
public class SessionProvider {

    /**
     * The currently authenticated user.
     */
    private User authenticatedUser;

    /**
     * Private constructor to prevent external instantiation.
     */
    private SessionProvider() {
    }

    private static class InstanceHolder {
        private static final SessionProvider instance = new SessionProvider();
    }

    /**
     * Retrieves the singleton instance of SessionProvider.
     *
     * @return The singleton instance of SessionProvider.
     */
    public static SessionProvider getInstance() {
        return InstanceHolder.instance;
    }

    /**
     * Retrieves the currently authenticated user.
     *
     * @return The authenticated user, or null if no user is authenticated.
     */
    public User getAuthenticatedUser() {
        return authenticatedUser;
    }

    /**
     * Sets the currently authenticated user.
     *
     * @param user The user to authenticate.
     */
    public void setAuthenticatedUser(User user) {
        authenticatedUser = user;
    }

    /**
     * Checks if a user is authenticated.
     *
     * @return True if a user is authenticated, false otherwise.
     */
    public boolean isAuthenticated() {
        return authenticatedUser != null;
    }

    /**
     * Clears the current session, logging out the authenticated user.
     */
    public void clearSession() {
        authenticatedUser = null;
        // Additional session cleanup can be added here if necessary
    }
}