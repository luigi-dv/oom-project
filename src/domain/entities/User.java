package src.domain.entities;

import src.domain.aggregate.Profile;
import src.domain.interfaces.ISearchable;

/**
 * Class representing a user
 */
public class User implements ISearchable {

    /**
     * The username of the user
     */
    private final String username;

    /**
     * The bio of the user
     */
    private String bio;

    /**
     * The password of the user
     */
    private String password;

    /**
     * The profile picture of the user
     */
    private String profilePicturePath;

    /**
     * The profile of the user
     */
    private Profile profile;

    /**
     * Constructor for User
     *
     * @param username username of the user
     * @param password password of the user
     * @param bio bio of the user
     */
    public User(String username, String password, String bio) {
        this.username = username;
        this.bio = bio;
        this.password = password;
        this.profilePicturePath = "resources/storage/images/" + username + ".png";
        this.profile = new Profile();
    }

    /**
     * Constructor for User
     *
     * @param username username of the user
     */
    public User(String username) {
        this.username = username;
        this.profilePicturePath = "resources/storage/images/" + username + ".png";
    }

    /**
     * Get the user's username
     *
     * @return String
     */
    public String getUsername() {
        return username;
    }

    /**
     * Get the user's bio
     *
     * @return String
     */
    public String getBio() {
        return bio;
    }

    /**
     * Set the user's bio
     *
     * @param bio The bio to set.
     */
    public void setBio(String bio) {
        this.bio = bio;
    }


    /**
     * Get the user's encrypted password
     *
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get the user's Profile Picture
     */
    public String getProfilePicturePath() {
        return profilePicturePath;
    }

    /**
     * Get the user's profile
     * @return UserProfile
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     * Set the user's profile
     * @param profile The profile to set.
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }


    @Override
    public String toString() {
        return username + ":" + password + ":" + bio;
    }

}