package src.domain.entities;

import java.util.ArrayList;
import java.util.List;

public class UserProfile {

    private List<Picture> pictures;
    private List<User> followingUsers;
    private List<User> followersUser;

    public UserProfile() {
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
    
    public void addPicture(Picture picture) {
        pictures.add(picture);
    }

}
