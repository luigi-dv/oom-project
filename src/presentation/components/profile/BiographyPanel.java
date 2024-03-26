package src.presentation.components.profile;

import src.domain.entities.User;

import javax.swing.*;
import java.awt.*;

public class BiographyPanel extends JPanel {

    private final User currentUser;

    public BiographyPanel(User currentUser) {
        super(new BorderLayout());
        this.currentUser = currentUser;
        initializePanel();
    }

    private void initializePanel() {
        removeAll(); // Clear existing content
        JLabel bioLabel = createBioLabel();
        add(bioLabel, BorderLayout.CENTER);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding to the bio panel
    }

    private JLabel createBioLabel() {
        JLabel bioLabel = new JLabel(currentUser.getBio(),
                SwingConstants.LEFT);
        bioLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        bioLabel.setForeground(Color.BLACK);
        return bioLabel;
    }
}
