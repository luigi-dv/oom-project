package src.application.services;

import java.util.List;
import src.domain.entities.User;
import src.domain.entities.notifications.FollowNotification;
import src.domain.entities.notifications.Notification;
import src.domain.aggregate.Follow;
import src.infrastructure.repositories.FollowRepository;

public class FollowService {
    /**
     * The picture repository
     */
    private final FollowRepository repository;
    private final NotificationService notificationService;

    /**
     * Constructor for PictureService
     */
    public FollowService() {
        this.repository = new FollowRepository();
        this.notificationService = new NotificationService();
    }

    /**
     * Save a picture
     *
     * @param follow The follow to be saved
     */
    public void save(Follow follow) {
        User user = follow.user();
        User follower = follow.follower();
        String message = follower.getUsername() + " followed you";
        System.out.println(user + " " +  follower);
        Notification notification = new FollowNotification(user, follower, message);
        notificationService.writeNotification(notification);
        repository.save(follow);
    }

    public boolean isFollowing(User user, User follower) {
        if (user.getUsername().equals(follower.getUsername())) {
            return true;
        }
        List<User> followers = getFollowersFromUser(user);
        for (User u : followers) {
            if (u.getUsername().equals(follower.getUsername())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get all followers from a user
     * @param user The user to get the follower from
     * @return A list of users
     */
    public List<User> getFollowersFromUser(User user) {
        return repository.findByUser(user);
    }

    /**
     * Get all users that a user is following
     * @param user The user to get the following users from
     * @return A list of users
     */
    public List<User> getFollowingFromUser(User user) {
        return repository.findByFollower(user);
    }
}
