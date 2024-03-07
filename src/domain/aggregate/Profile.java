package src.domain.aggregate;

import src.domain.entities.Picture;
import src.domain.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Aggregate class that represents a user's profile on Quackstagram.
 */
public class Profile {

    private List<Picture> pictures;
    private List<User> followingUsers;
    private List<User> followersUser;

    public Profile() {
        this.pictures = new ArrayList<>();
        this.followingUsers = new ArrayList<>();
        this.followersUser = new ArrayList<>();
    }

    public int getPostsCount() {
        return pictures.size();
    }
    public int getFollowersCount() {
        return followersUser.size();
    }
    public int getFollowingCount() {
        return followingUsers.size();
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    /**
     * Set the list of users that the user is following.
     * @param pictures The list of pictures to set.
     */
    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    /**
     * Set the list of users that the user is following.
     * @param followingUsers The list of users to set.
     */
    public void setFollowingUsers(List<User> followingUsers) {
        this.followingUsers = followingUsers;
    }

    /**
     * Set the list of users that are following the user.
     * @param followersUser The list of users to set.
     */
    public void setFollowersUser(List<User> followersUser) {
        this.followersUser = followersUser;
    }
    
    public void addPicture(Picture picture) {
        pictures.add(picture);
    }

    public void addFollowing(User user) {
        followingUsers.add(user);
    }
    public void addFollower(User user) {
        followersUser.add(user);
    }




}
