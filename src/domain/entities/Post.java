package src.domain.entities;

import java.util.List;

public class Post {
    private final Picture picture;
    private final List<Like> likes;
    private final List<Comment> comments;

    public Post(Picture picture, List<Like> likes, List<Comment> comments) {
        this.picture = picture;
        this.likes = likes;
        this.comments = comments;
    }

    public void addLike(Like like) {
        likes.add(like);
    }

    public void removeLike(Like like) {
        likes.remove(like);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public Picture getPicture() { return picture; }

    public List<Comment> getComments() { return comments; }
    public void deleteComment(Comment comment) {
    comments.remove(comment);
    }
}
