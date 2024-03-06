package src.domain.aggregates;

import src.domain.entities.Comment;
import src.domain.entities.Like;
import src.domain.entities.Picture;

import java.util.ArrayList;
import java.util.List;

public class Post {
    private final Picture picture;
    private final List<Like> likes;
    private final List<Comment> comments;

    public Post(Picture picture) {
        this.picture = picture;
        this.likes = new ArrayList<>();
        this.comments = new ArrayList<>();
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

    public Picture getPicture() {
        return picture;
    }

    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }

    public void deleteComment(Comment comment) {
        comments.remove(comment);
    }

    public List<Like> getLikes() {
        return new ArrayList<>(likes);
    }

    public void deleteLike(Like like) {
        likes.remove(like);
    }
}
