package src.infrastructure.utilities.file.reader;

import java.util.List;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import src.domain.entities.User;
import src.infrastructure.utilities.file.IFile;

/**
 * Utility class for reading followers and following users from a file.
 * Implements the IFile interface for generic file reading functionality.
 */
public class FollowingReader implements IFile {

    /**
     * Path to the file storing follower and following information.
     */
    private static final String FILE_PATH = FILE_PATH_ROOT + "follow.txt";

    /**
     * Retrieves the list of followers for a specific user.
     *
     * @param user The user to get the followers of.
     * @return The list of users following the specified user.
     */
    public static List<User> getFollowersFromUser(User user) {
        String username = user.getUsername();
        List<User> followers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(username + ":")) {
                    // User entry found, parse the line and construct a User object
                    followers.add(parseFollowerFromLine(line));
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle IOException appropriately
        } catch (Exception e) {
            e.printStackTrace(); // Handle other exceptions appropriately
        }

        return followers;
    }

    /**
     * Parses a User object from a line in the follower's file.
     *
     * @param line The line to parse.
     * @return The User object representing a follower, parsed from the line.
     */
    private static User parseFollowerFromLine(String line) {
        String[] parts = line.split(":");
        String followerUsername = parts[1];
        return new User(followerUsername);
    }

    /**
     * Retrieves the list of users that a specific user is following.
     *
     * @param user The user to get the following users of.
     * @return The list of users that the specified user is following.
     */
    public static List<User> getFollowingUsers(User user) {
        String username = user.getUsername();
        List<User> followingUsers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User followingUser = parseFollowingFromLine(line, username);
                if (followingUser != null) {
                    followingUsers.add(followingUser);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle IOException appropriately
        } catch (Exception e) {
            e.printStackTrace(); // Handle other exceptions appropriately
        }

        return followingUsers;
    }

    /**
     * Parses a User object from a line in the following users file.
     *
     * @param line     The line to parse.
     * @param username The username of the user for whom the following users are being retrieved.
     * @return The User object representing a user being followed, parsed from the line.
     */
    private static User parseFollowingFromLine(String line, String username) {
        String[] parts = line.split(":");
        String followedUsername = parts[1];
        if (!followedUsername.equals(username)) {
            return null;
        }
        return new User(parts[0]);
    }
}
