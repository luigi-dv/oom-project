package src.presentation.components.search;


import src.domain.entities.User;
import java.awt.event.MouseAdapter;
import src.presentation.components.ui.UserCardComponent;
import src.presentation.interfaces.IUserSearchListener;

/**
 * A component that displays a user in a search result.
 */
public class UserSearchComponent extends UserCardComponent {

    private final User user;
    private IUserSearchListener listener;

    /**
     * Creates a new UserSearchComponent.
     * @param user The user to display.
     * @param mouseAdapter The mouse adapter to use.
     */
    public UserSearchComponent(User user, MouseAdapter mouseAdapter) {
        super(user);
        this.user = user;
        addListeners();
    }

    /**
     * Sets the user search listener.
     * @param listener The listener to set.
     */
    public void setUserSearchListener(IUserSearchListener listener) {
        this.listener = listener;
    }

    /**
     * Adds listeners to the component.
     */
    private void addListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                listener.displayUserDetails(UserSearchComponent.this, user);
            }
        });
    }
}
