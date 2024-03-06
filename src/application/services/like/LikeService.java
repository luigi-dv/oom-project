package src.application.services.like;

import src.application.providers.SessionProvider;
import src.domain.entities.Like;
import src.domain.aggregates.Post;
import src.domain.entities.User;
import src.infrastructure.repositories.like.LikeRepository;

/**
 * Service class responsible for managing like-related operations in the application.
 */
public class LikeService {

    /**
     * The repository for handling like data.
     */
    private final LikeRepository likeRepository;
    private final SessionProvider sessionProvider;

    /**
     * Constructs a LikeService instance, initializing the LikeRepository.
     */
    public LikeService(SessionProvider sessionProvider) {
        this.likeRepository = new LikeRepository();
        this.sessionProvider = sessionProvider;
    }

    /**
     * Likes a post on behalf of a user.
     *
     * @param post The post to be liked.
     */
    public void likePost(Post post) {
        if (sessionProvider.isAuthenticated()) {
            Like like = new Like(sessionProvider.getAuthenticatedUser(), post);
            likeRepository.save(like);
        } else {
            // Handle not authenticated
        }
    }

    /**
     * Unlikes a post by removing the corresponding like.
     *
     * @param like The like entity representing the like to be removed.
     */
    public void unlikePost(Like like) {
        if (sessionProvider.isAuthenticated()) {
            User likeUser = like.getUser();
            User authenticatedUser = sessionProvider.getAuthenticatedUser();
            if (!likeUser.getId().equals(authenticatedUser.getId())) {
                throw new IllegalArgumentException("User does not have permission to remove this like.");
            } else {
                likeRepository.delete(like.getId());
            }
        } else {
            // Handle not authenticated
        }
    }
}
