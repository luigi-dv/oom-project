package src.domain.entities;

import java.util.UUID;
import src.infrastructure.utilities.Crypter;


public class User {

    private UUID id;
    private String username;
    private String bio;
    private String password;

    private UserProfile profile;

    public User(String username, String bio, String password) {
        this.username = username;
        this.bio = bio;
        this.password = password;
        this.profile = new UserProfile();
    }

    public UUID getId() {
        return id;
    }

    public User(String username) {
        this.username = username;
    }

    // Getter and Setters methods for user details
    public String getUsername() {
        return username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    // Implement the toString method for saving user information
    @Override
    public String toString() {
        try {
            return Crypter.StringToEncryptedString(username) + ":" + Crypter.StringToEncryptedString(password) + ":" + Crypter.StringToEncryptedString(bio);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return username + ":" + password + ":" + bio;
    }


    /**
     * Get the user's encrypted password
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
    public UserProfile getProfile() {
        return profile;
    }

}