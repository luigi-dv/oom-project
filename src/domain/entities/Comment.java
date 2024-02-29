package src.domain.entities;

public class Comment {

    private User user;
    private String text;
    private int likes;

    public Comment(User user, String text) {
        this.user = user;
        this.text = text;
        this.likes = 0;
    }

    public User getUser() {
        return user;
    }

    public String getText() {
        return text;
    }

    public int getLikes() {
        return likes;
    }

    public void addLike() {
        likes++;
    }
    
}
