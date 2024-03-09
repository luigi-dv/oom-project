package src.domain.entities;

import src.domain.aggregate.Profile;

/**
 * Class representing a user
 */
public class User {

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
        this.profile = new Profile();
    }

    /**
     * Constructor for User
     *
     * @param username username of the user
     */
    public User(String username) {
        this.username = username;
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
     * Set the user's password
     *
     * @return String
     */
    @Override
    public String toString() {
//        try {
//            return Crypter.StringToEncryptedString(username) + ":" + Crypter.StringToEncryptedString(password) + ":" + Crypter.StringToEncryptedString(bio);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return username + ":" + password + ":" + bio;
    }


    /**
     * Get the user's encrypted password
     *
     * @return String
     */
    public String getPassword() {
        // TODO: (Alert) - User Domain Service to return encrypted password
        return password;
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

}