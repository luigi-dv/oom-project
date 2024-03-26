package src.presentation.controllers.buttons;

import src.application.services.FollowService;
import src.domain.aggregate.Follow;
import src.domain.entities.User;

import java.time.LocalDateTime;

public class FollowButtonController {
    private final FollowService followService;

    public FollowButtonController() {
        this.followService = new FollowService();
    }

    /**
     * Check if a user is following another user
     * @param user The user to check if they are following
     * @param follower The user to check if they are following
     * @return True if the user is following the follower, false otherwise
     */
    public boolean isFollowing(User user, User follower) {
        return followService.isFollowing(user, follower);
    }

    /**
     * Follow a user
     * @param user The user to follow
     * @param follower The user that is following
     */
    public void followUser(User user, User follower) {
        Follow follow = new Follow(follower, user, LocalDateTime.now());
        followService.save(follow);
    }
}
