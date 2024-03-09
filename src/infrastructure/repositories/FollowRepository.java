package src.infrastructure.repositories;

import src.domain.aggregate.Follow;
import src.domain.entities.User;
import src.domain.repositiories.IFollowRepository;
import src.infrastructure.utilities.file.reader.FollowingReader;
import src.infrastructure.utilities.file.writer.Follower;

import java.util.List;

public class FollowRepository implements IFollowRepository {

    /**
     * Finds the followers by the username of the user aggregate property.
     *
     * @param user The username of the user aggregate property.
     * @return The list of users following the user.
     */
    public List<User> findByUser(User user) {
        return FollowingReader.getFollowersFromUser(user);
    }

    /**
     * Finds the users that a user is following.
     *
     * @param user The user to get the following users of.
     * @return The list of users that the user is following.q
     */
    public List<User> findByFollower(User user) {
        return FollowingReader.getFollowingUsers(user);
    }

    /**
     * Creates a new user with the provided username and password.
     * @param follow The follow aggregate to save.
     * @return the created follow aggregate.
     */
    public Follow save(Follow follow) {
        return Follower.writeToFile(follow);
    }


    @Override
    public Follow update(Follow follow) {
        return null;
    }

    @Override
    public Follow delete(Follow follow) {
        return null;
    }
}
