package src.infrastructure.repositories;

import src.domain.aggregate.Follow;
import src.domain.entities.User;
import src.domain.repositiories.IFollowRepository;
import src.infrastructure.utilities.filereaders.FollowerReader;
import src.infrastructure.utilities.filewriter.FollowerWriter;

import java.util.List;

public class FollowRepository implements IFollowRepository {

    /**
     * Finds the followers by the username of the user aggregate property.
     *
     * @param user The username of the user aggregate property.
     * @return The list of users following the user.
     */
    public List<User> findByUser(User user) {
        return FollowerReader.getFollowersFromUser(user);
    }

    /**
     * Finds the users that a user is following.
     *
     * @param user The user to get the following users of.
     * @return The list of users that the user is following.q
     */
    public List<User> findByFollower(User user) {
        return FollowerReader.getFollowingUsers(user);
    }

    /**
     * Creates a new user with the provided username and password.
     * @param follow The follow aggregate to save.
     * @return the created follow aggregate.
     */
    public Follow save(Follow follow) {
        return FollowerWriter.writeToFile(follow);
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
