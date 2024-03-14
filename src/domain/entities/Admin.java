package src.domain.entities;

public class Admin extends User {
    private boolean active;

    public Admin(String username, String bio, String password) {
        super(username, bio, password);
        this.active = true;
    }

    public boolean isActive() {
        return active;
    }

    public void deactivate() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }
}
