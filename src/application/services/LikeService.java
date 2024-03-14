package src.application.services;


import src.domain.entities.Like;
import src.domain.entities.User;
import src.domain.interfaces.ILikeable;
import src.application.providers.SessionProvider;
import src.infrastructure.repositories.LikeRepository;

/**
 * Service class responsible for managing like-related operations in the application.
 */
public class LikeService<T extends ILikeable> {
//GPT
    /**
     * The repository for handling like data.
     */
    private final LikeRepository<T> repository;
    private final SessionProvider sessionProvider;

    /**
     * Constructs a LikeService instance, initializing the LikeRepository.
     */
    public LikeService(SessionProvider sessionProvider) {
        this.repository = new LikeRepository<T>();
        this.sessionProvider = sessionProvider;
    }

    /**
     * Likes a post on behalf of a user.
     *
     * @param content The post to be liked.
     */
    public void like(T content) {
        if (sessionProvider.isAuthenticated()) {
            Like<T> like = new Like<T>(sessionProvider.getAuthenticatedUser(), content);
            repository.save(like);
        } else {
            // Handle not authenticated
            System.out.println("You must be authenticated to like a post.");
        }
    }

    /**
     * Unlikes a post by removing the corresponding like.
     *
     * @param like The like entity representing the like to be removed.
     */
    public void unlikePost(Like<T> like) {
        if (sessionProvider.isAuthenticated()) {
            User likeUser = like.getUser();
            User authenticatedUser = sessionProvider.getAuthenticatedUser();
            if (!likeUser.getUsername().equals(authenticatedUser.getUsername())) {
                throw new IllegalArgumentException("User does not have permission to remove this like.");
            } else {
                repository.delete(like.getId());
            }
        } else {
            // Handle not authenticated
            System.out.println("You must be authenticated to like a post.");
        }
    }
}
