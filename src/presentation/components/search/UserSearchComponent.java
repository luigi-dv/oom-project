package src.presentation.components.search;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import src.domain.entities.User;
import src.presentation.components.ui.AvatarImagePanel;
import src.presentation.interfaces.UIConstants;

public class UserSearchComponent extends JPanel {

    private final User user;
    private final MouseAdapter mouseAdapter;

    public UserSearchComponent(User user, MouseAdapter mouseAdapter) {
        super();
        this.user = user;
        this.mouseAdapter = mouseAdapter;
        initializePanel();
    }

    public void initializePanel() {
        removeAll();

        setMaximumSize(new Dimension(UIConstants.WIDTH, 70));
        setPreferredSize(new Dimension(UIConstants.WIDTH, 70)); 
        // Choose an appropriate layout manager, such as FlowLayout or BoxLayout
        setLayout(new FlowLayout(FlowLayout.LEFT)); // Align components to the left
    
        setBorder(new LineBorder(Color.BLACK));
    
        JPanel imagePanel = new AvatarImagePanel(user.getProfilePicturePath(), 50, 50);
        JLabel usernameLabel = new JLabel(user.getUsername());
    
        addMouseListener(mouseAdapter);
    
        add(imagePanel);
        add(usernameLabel);
    }

}
