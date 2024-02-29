package src.domain.entities;

// Represents a user on Quackstagram
public class User {

    private String username;
    private String bio;
    private String password;

    public User(String username, String bio, String password) {
        this.username = username;
        this.bio = bio;
        this.password = password;
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
        return username + ":" + bio + ":" + password; // Format as needed
    }

}