package src.application.services.like;

import src.domain.entities.Like;
import src.domain.entities.Post;
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

    /**
     * Constructs a LikeService instance, initializing the LikeRepository.
     */
    public LikeService() {
        this.likeRepository = new LikeRepository();
    }

    /**
     * Likes a post on behalf of a user.
     *
     * @param user The user who is liking the post.
     * @param post The post to be liked.
     */
    public void likePost(User user, Post post) {
        Like like = new Like(user, post);
        likeRepository.save(like);
    }

    /**
     * Unlikes a post by removing the corresponding like.
     *
     * @param like The like entity representing the like to be removed.
     */
    public void unlikePost(Like like) {
        likeRepository.delete(like.getId());
    }
}
