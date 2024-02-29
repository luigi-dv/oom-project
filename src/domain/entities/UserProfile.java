package src.domain.entities;

import java.util.ArrayList;
import java.util.List;

public class UserProfile {

    private int postsCount;
    private int followersCount;
    private int followingCount;
    private List<Picture> pictures;

    public UserProfile() {
        this.postsCount = 0;
        this.followersCount = 0;
        this.followingCount = 0;
        this.pictures = new ArrayList<>();
    }

    public int getPostsCount() {
        return postsCount;
    }
    public void setPostsCount(int postsCount) {
        this.postsCount = postsCount;
    }
    public int getFollowersCount() {
        return followersCount;
    }
    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }
    public int getFollowingCount() {
        return followingCount;
    }
    public void setFollowingCount(int followingCount) {
        this.followingCount = followingCount;
    }
    public List<Picture> getPictures() {
        return pictures;
    }
    
    public void addPicture(Picture picture) {
        pictures.add(picture);
        postsCount++;
    }

}
