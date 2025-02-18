package src.domain.entities;

import src.domain.interfaces.ILikeable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * Represents a comment on a picture
 */
public class Comment implements ILikeable {
    private final UUID id;
    private User user;
    private Picture picture;
    private String text; 
    private List<Like<Comment>> likes;

    /**
     * Create a new comment
     *
     * @param user the user who created the comment
     * @param picture the picture the comment was made on
     * @param text the text of the comment
     */
    public Comment(User user, Picture picture, String text) {
        this.id = UUID.randomUUID();
        this.user = user;
        this.picture = picture;
        this.text = text;
        this.likes = new ArrayList<>();
    }

    /**
     * Create a new comment
     *
     * @param id the unique identifier of the comment
     * @param user the user who created the comment
     * @param picture the picture the comment was made on
     * @param text the text of the comment
     */
    public Comment(UUID id, User user, Picture picture, String text) {
        this.id = id;
        this.user = user;
        this.picture = picture;
        this.text = text;
        this.likes = new ArrayList<>();
    }

    /**
     * Create a new comment
     *
     * @param id the unique identifier of the comment
     */
    public Comment(UUID id) {
        this.id = id;
    }

    /**
     * Get the unique identifier of the comment
     *
     * @return the unique identifier of the comment
     */
    public UUID getId() {
        return id;
    }

    /**
     * Get the user who created the comment
     *
     * @return the user who created the comment
     */
    public User getUser() {
        return user;
    }

    /**
     * Get the picture the comment was made on
     *
     * @return the picture the comment was made on
     */
    public String getText() {
        return text;
    }

    /**
     * Get the picture the comment was made on
     *
     * @return the picture the comment was made on
     */
    public List<Like<Comment>> getLikes() {
        return likes;
    }

    /**
     * Add a like to the comment
     * @param user the user who liked the comment
     * @param comment the comment that was liked
     */
    public void addLike(User user, Comment comment) {
        addLike(new Like<>(user, comment));
    }

    public void addLike(Like<Comment> like) {
        this.likes.add(like);
    }

    /**
     * Get the type of the comment
     *
     * @return the type of the comment
     */
    public String getType() {
        return "comment";
    }


    @Override
    public String toString() {
        return id.toString() + picture.getId().toString() + ":" + user.getUsername() + ":" + text;
    }


    
}
