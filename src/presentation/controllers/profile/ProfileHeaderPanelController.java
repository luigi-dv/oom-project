package src.presentation.controllers.profile;

import src.application.providers.SessionProvider;
import src.application.services.FollowService;
import src.domain.entities.User;

public class ProfileHeaderPanelController {

    private final SessionProvider sessionProvider;
    private final FollowService followService;

    public ProfileHeaderPanelController() {
        this.sessionProvider = SessionProvider.getInstance();
        this.followService = new FollowService();
    }

    /**
     * Check if the user is the authenticated user
     * @param user The user to check
     * @return True if the user is the authenticated user, false otherwise
     */
    public boolean isAuthenticatedUser(User user) {
        return sessionProvider.getAuthenticatedUser().getUsername().equals(user.getUsername());
    }

    /**
     * Check if the authenticated user is following the user
     * @return True if the authenticated user is following the user, false otherwise
     */
    public User getAuthenticatedUser() {
        return sessionProvider.getAuthenticatedUser();
    }

    /**
     * Check if the user is following the follower
     * @param user The user to check
     * @param follower The follower to check
     * @return True if the user is following the follower, false otherwise
     */
    public Boolean isFollowing(User user, User follower) {
        return followService.isFollowing(user, follower);
    }
}
