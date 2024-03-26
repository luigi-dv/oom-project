package src.presentation.components.buttons;

import src.domain.entities.User;
import src.presentation.controllers.buttons.FollowButtonController;

import java.awt.*;


public class FollowButton extends ButtonComponent {

    private final User user;
    private final User follower;

    private final FollowButtonController controller;

    /**
     * Constructor for the FollowButton
     * @param following The current following status
     * @param user The user to follow
     * @param follower The user that is following
     */
    public FollowButton(Boolean following, User user, User follower){
        super(following ? "Unfollow" : "Follow", 14, 5, Component.CENTER_ALIGNMENT, "primary", false);
        this.user = user;
        this.follower = follower;
        this.controller = new FollowButtonController();
        addActionListener(e -> toggleFollow());
    }

    /**
     * Toggles the follow status of the user
     */
    public void toggleFollow(){
        if (controller.isFollowing(user, follower)){
            setEnabled(false);
            setText("Following");
        } else {
            setEnabled(true);
            controller.followUser(user, follower);
            setText("Follow");
        }
    }
}
