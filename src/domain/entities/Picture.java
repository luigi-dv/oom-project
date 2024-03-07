package src.domain.entities;

import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

// Represents a picture on Quackstagram
public class Picture implements ILikeable {
    private final UUID id;
    private User user;
    private String imagePath;
    private String caption;
    private List<Like<Picture>> likes;
    private List<Comment> comments;

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

    public Picture(UUID id) {
        this.id = id;
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
    // Getter methods for picture details
    public String getImagePath() { return imagePath; }
    public String getCaption() { return caption; }

    public List<Comment> getComments() { return comments; }

    public void deleteComment(Comment comment) {
        comments.remove(comment);
    }

    public void deleteLike(Like<Picture> like) {
        likes.remove(like);
    }

    public String getType() {
        return "picture";
    }

    @Override
    public String toString() {
        return id.toString() + ":" + user.getUsername() + ":" + imagePath + ":" + caption;
    }

}
