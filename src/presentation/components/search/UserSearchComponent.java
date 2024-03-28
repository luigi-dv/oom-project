package src.presentation.components.search;

import javax.swing.*;
import java.awt.*;
import src.domain.entities.User;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import src.presentation.components.ui.UserCardComponent;
import src.presentation.interfaces.IUserSearchListener;
import src.presentation.interfaces.UIConstants;

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
        setMaximumSize(new Dimension(UIConstants.WIDTH, 70));
        setPreferredSize(new Dimension(UIConstants.WIDTH, 70));
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
            public void mouseEntered(MouseEvent e) {
                setBackgroundAndChildren(Color.LIGHT_GRAY);
            }
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                listener.displayUserDetails(UserSearchComponent.this, user);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                setBackgroundAndChildren(Color.WHITE);
            }
        });
    }

    /**
     * Sets the background color of the panel and its children.
     * @param color The color to set the background to.
     */
    private void setBackgroundAndChildren(Color color) {
        setBackground(color);
        for (Component component : getComponents()) {
            if (component instanceof JPanel) {
                ((JPanel) component).setBackground(color);
            }
        }
    }
}
