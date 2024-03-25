package src.presentation.layouts;

import java.awt.*;
import javax.swing.*;
import src.presentation.Router;
import src.presentation.interfaces.UIConstants;


public class GuestLayout extends JPanel {
    /**
     * The router to use for navigation.
     */
    protected final Router router;


    /**
     * Creates a new AuthenticatedLayout with the specified view and router.
     * @param view The view to use for the layout.
     * @param router The router to use for navigation.
     */
    public GuestLayout(JPanel view, Router router) {
        this.router = router;

        // Set the layout of the panel
        setLayout(new GridBagLayout());

        // Create and configure GridBagConstraints for centering the components vertically
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel photoPanel = createPhotoPanel();

        // Add the photoPanel to the layout
        add(photoPanel, gbc);
        gbc.gridy++; // Move to the next row
        add(view, gbc);
        gbc.gridy++; // Move to the next row
        // Bottom: add(photoPanel, gbc);
    }

    /**
     * Creates a panel containing the header of the layout.
     * @return The header panel.
     */
    private static JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBackground(new Color(51, 51, 51)); // Set a darker background for the header
        JLabel lblRegister = new JLabel("Quackstagram 🐥");
        lblRegister.setFont(new Font("Arial", Font.BOLD, 16));
        lblRegister.setForeground(Color.WHITE); // Set the text color to white
        headerPanel.add(lblRegister);
        headerPanel.setPreferredSize(new Dimension(UIConstants.WIDTH, 40));
        return headerPanel;
    }

    /**
     * Creates a panel containing the photo of the layout.
     * @return The photo panel.
     */
    private static JPanel createPhotoPanel() {
        JPanel photoPanel = new JPanel();
        JLabel photoLabel = createPhotoLabel();
        photoPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        photoPanel.add(photoLabel);
        return photoPanel;
    }

    /**
     * Creates a label containing the photo of the layout.
     * @return The photo label.
     */
    private static JLabel createPhotoLabel() {
        JLabel photoLabel = new JLabel();
        photoLabel.setPreferredSize(new Dimension(80, 80));
        photoLabel.setHorizontalAlignment(JLabel.CENTER);
        photoLabel.setVerticalAlignment(JLabel.CENTER);
        photoLabel.setIcon(new ImageIcon(
                new ImageIcon("resources/images/logos/logo.png").getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)));
        return photoLabel;
    }
}
