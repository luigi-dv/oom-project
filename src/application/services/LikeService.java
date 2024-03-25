package src.application.services;

import src.domain.entities.Like;
import src.domain.entities.Picture;
import src.domain.entities.User;
import src.domain.entities.notifications.PictureLikeNotification;
import src.domain.interfaces.ILikeable;

import java.util.List;

import src.application.providers.SessionProvider;
import src.infrastructure.repositories.LikeRepository;

/**
 * Service class responsible for managing like-related operations in the application.
 */
public class LikeService<T extends ILikeable> {
    
    private final LikeRepository<T> repository;
    private final SessionProvider sessionProvider;
    private final LikeRepository<Picture> likeRepositoryPicture;
    private final NotificationService notificationService;

    /**
     * Constructs a LikeService instance, initializing the LikeRepository.
     */
    public LikeService(SessionProvider sessionProvider) {
        this.repository = new LikeRepository<T>();
        this.sessionProvider = sessionProvider;
        this.likeRepositoryPicture = new LikeRepository<>();
        this.notificationService = new NotificationService();
    }

    /**
     * Likes a post on behalf of a user.
     *
     * @param content The post to be liked.
     */
    public boolean like(Picture content) {
        if (sessionProvider.isAuthenticated()) {
            User user = sessionProvider.getAuthenticatedUser();
            List<Like<Picture>> likes = likeRepositoryPicture.findByPostId(content.getId());
            for (Like<Picture> like : likes) {
                if (like.getUser().getUsername().equals(user.getUsername())) {
                    return false;
                }
            }
            Like<Picture> like = new Like<>(user, content);
            likeRepositoryPicture.save(like);
            content.addLike(like);
            String message = user.getUsername() + " liked your picture";
            PictureLikeNotification notification = new PictureLikeNotification(user, content.getUser(), message);
            notificationService.writeNotification(notification);
            return true;    
        } else {
            // Handle not authenticated
            System.out.println("You must be authenticated to like a post.");
            return false;
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
