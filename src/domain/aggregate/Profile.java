package src.domain.aggregate;

import src.domain.entities.Picture;
import src.domain.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Profile} class represents a user's profile on Quackstagram.
 * It encapsulates information about the user's posts, followers, and users they are following.
 *
 * @author [Your Name]
 * @version 1.0
 */
public class Profile {

    /** The list of pictures posted by the user. */
    private List<Picture> pictures;

    /** The list of users that the user is following. */
    private List<User> followingUsers;

    /** The list of users who are following the user. */
    private List<User> followersUser;

    /**
     * Constructs a new instance of the {@code Profile} class.
     * Initializes the lists for pictures, following users, and followers.
     */
    public Profile() {
        this.pictures = new ArrayList<>();
        this.followingUsers = new ArrayList<>();
        this.followersUser = new ArrayList<>();
    }

    /**
     * Gets the count of posts (pictures) in the user's profile.
     *
     * @return The number of posts.
     */
    public int getPostsCount() {
        return pictures.size();
    }

    /**
     * Gets the count of followers for the user.
     *
     * @return The number of followers.
     */
    public int getFollowersCount() {
        return followersUser.size();
    }

    /**
     * Gets the count of users that the user is following.
     *
     * @return The number of following users.
     */
    public int getFollowingCount() {
        return followingUsers.size();
    }

    /**
     * Gets the list of pictures posted by the user.
     *
     * @return The list of pictures.
     */
    public List<Picture> getPictures() {
        return pictures;
    }

    /**
     * Sets the list of pictures posted by the user.
     *
     * @param pictures The list of pictures to set.
     */
    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    /**
     * Sets the list of users that the user is following.
     *
     * @param followingUsers The list of users to set.
     */
    public void setFollowingUsers(List<User> followingUsers) {
        this.followingUsers = followingUsers;
    }

    /**
     * Sets the list of users who are following the user.
     *
     * @param followersUser The list of users to set.
     */
    public void setFollowersUser(List<User> followersUser) {
        this.followersUser = followersUser;
    }

    /**
     * Adds a picture to the user's profile.
     *
     * @param picture The picture to add.
     */
    public void addPicture(Picture picture) {
        pictures.add(picture);
    }

    /**
     * Adds a user to the list of users that the user is following.
     *
     * @param user The user to add to the following list.
     */
    public void addFollowing(User user) {
        followingUsers.add(user);
    }

    /**
     * Adds a user to the list of users who are following the user.
     *
     * @param user The user to add to the followers list.
     */
    public void addFollower(User user) {
        followersUser.add(user);
    }
}
