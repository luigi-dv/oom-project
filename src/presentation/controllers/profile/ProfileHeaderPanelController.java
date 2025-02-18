package src.presentation.controllers.profile;

import src.application.providers.SessionProvider;
import src.application.services.ChatService;
import src.application.services.FollowService;
import src.domain.entities.User;
import src.domain.entities.messages.Chat;

public class ProfileHeaderPanelController {

    private final SessionProvider sessionProvider;
    private final FollowService followService;
    private final ChatService chatService;

    public ProfileHeaderPanelController() {
        this.sessionProvider = SessionProvider.getInstance();
        this.followService = new FollowService();
        this.chatService = new ChatService();
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

    /**
     * Retrieve the chat between two users or create a new one if it doesn't exist.
     * @param userA The user to start a chat with
     * @param userB The user to start a chat with
     */
    public Chat startChat(User userA, User userB) {
        return chatService.getChatBetweenUsers(userA, userB);
    }
}
