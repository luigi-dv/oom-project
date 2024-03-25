package src.domain.entities;

import src.domain.interfaces.ILikeable;
import src.domain.interfaces.ISearchable;

import java.util.List;
import java.util.UUID;
import java.util.ArrayList;

/**
 * Class representing a picture
 */
public class Picture  implements  ILikeable, ISearchable {

    /**
     * The unique identifier for the picture
     */
    private final UUID id;

    /**
     * The user who posted the picture
     */
    private User user;

    /**
     * The caption for the picture
     */
    private String caption;

    /**
     * The path to the image file
     */
    private String imagePath;

    /**
     * The comments on the picture
     */
    private List<Comment> comments;

    /**
     * The likes on the picture
     */
    private List<Like<Picture>> likes;

    private List<HashTag> hashTags;

    /**
     * Constructor for Picture when the ID is not known
     * @param imagePath The path to the image file
     * @param caption the caption for the picture
     */
    public Picture(User user, String imagePath, String caption) {
        this.user = user;
        this.id = UUID.randomUUID();
        this.imagePath = imagePath;
        this.caption = caption;
        this.likes = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public Picture(UUID id, User user, String imagePath, String caption) {
        this.user = user;
        this.id = id;
        this.imagePath = imagePath;
        this.caption = caption;
        this.likes = new ArrayList<>();
        this.comments = new ArrayList<>();

    }

    public Picture(UUID id, User user, String imagePath, String caption, List<HashTag> hashTags) {
        this.user = user;
        this.id = id;
        this.imagePath = imagePath;
        this.caption = caption;
        this.likes = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.hashTags = hashTags;
    }

    public Picture(User user, String imagePath, String caption, List<HashTag> hashTags) {
        this.user = user;
        this.id = UUID.randomUUID();
        this.imagePath = imagePath;
        this.caption = caption;
        this.likes = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public Picture(UUID id) {
        this.id = id;
    }

    public Picture(UUID id, User user) {
        this.id = id;
        this.user = user;
    }

    /**
     * Add a comment to the picture
     * @param comment The comment to be added
     */
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    /**
     * Add a like to the picture
     * @param pictureLike The like to be added
     */
    public void addLike(Like<Picture> pictureLike){
        likes.add(pictureLike);
    }

    public UUID getId() {
        return id;
    }

    /**
     * Get the image path
     * @return The path to the image file
     */
    public String getImagePath() { return imagePath; }

    /**
     * Get the caption for the picture
     * @return The caption for the picture
     */
    public String getCaption() { return caption; }

    /**
     * Get the user who posted the picture
     * @return The user who posted the picture
     */
    public List<Comment> getComments() { return comments; }

    /**
     * Get the user who posted the picture
     * @param comment The comment to be deleted
     */
    public void deleteComment(Comment comment) {
        comments.remove(comment);
    }

    /**
     * Get the pictures likes
     * @return The list of likes for the picture
     */
    public List<Like<Picture>> getLikes() {
        return likes;
    }

    /**
     * Get the user who posted the picture
     * @param like The like to be deleted
     */
    public void deleteLike(Like<Picture> like) {
        likes.remove(like);
    }

    public User getUser() {
        return user;
    }

    /**
     * Get the user who posted the picture
     * @return The user who posted the picture
     */
    public String getType() {
        return "picture";
    }

    public List<HashTag> getHashTags() {
        return hashTags;
    }

    @Override
    public String toString() {
        return id.toString() + ":" + user.getUsername() + ":" + imagePath + ":" + caption;
    }

    public void setLikes(List<Like<Picture>> likes) {
        this.likes = likes;
    }

}
