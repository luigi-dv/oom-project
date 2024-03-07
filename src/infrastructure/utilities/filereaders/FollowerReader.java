package src.infrastructure.utilities.filereaders;

import src.domain.entities.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FollowerReader {

    private static final String credentialsFilePath = "src/infrastructure/persistance/date/follow.txt";

    /**
     * Get the followers of a user.
     *
     * @param user The user to get the followers of.
     * @return The list of users following the user.
     */
    public static List<User> getFollowersFromUser(User user) {
        String username = user.getUsername();
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(credentialsFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(username + ":")) {
                    // User entry found, parse the line and construct a User object
                    users.add(parseFollowerFromLine(line));
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle IOException appropriately
        } catch (Exception e) {
            e.printStackTrace(); // Handle other exceptions appropriately
        }

        return users;
    }

    /**
     * Parses a User object from a line in the credentials file.
     * @param line The line to parse.
     * @return The User object parsed from the line.
     */
    private static User parseFollowerFromLine(String line) {
        String[] parts = line.split(":");
        String follower = parts[1];
        return new User(follower);
    }

    /**
     * Get the users that a user is following.
     * @param user The user to get the following users of.
     * @return The list of users that the user is following.
     */
    public static List<User> getFollowingUsers(User user) {
        String username = user.getUsername();
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(credentialsFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User getUser = parseFollowingFromLine(line, username);
                if (getUser != null) {
                    users.add(getUser);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle IOException appropriately
        } catch (Exception e) {
            e.printStackTrace(); // Handle other exceptions appropriately
        }

        return users;
    }

    private static User parseFollowingFromLine(String line, String username) {
        String[] parts = line.split(":");
        String user = parts[1];
        if (!user.equals(username)) {
            return null;
        }
        return new User(parts[0]);
    }

}
