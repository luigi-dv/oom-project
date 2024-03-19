package src.presentation.components.navigation;


import java.awt.*;
import javax.swing.*;
import src.presentation.Router;
import src.presentation.components.buttons.NavigationIconButton;
import src.presentation.components.ui.ScaledIcon;

public class InteractionBarNavigation extends JPanel {
    /**
     * Creates the interaction bar for the GUI.
     * @param router Router
     */
    public InteractionBarNavigation(Router router) {
        // Set layout to BoxLayout for horizontal arrangement
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        /// Panel to hold "For you" label and chevron icon
        JPanel forYouPanel = CustomizedPanel();
        add(forYouPanel);

        // Add glue to push components to the right
        add(Box.createHorizontalGlue());

        // Notification button with heart icon
        add(new NavigationIconButton("resources/images/icons/heart.png", "notifications", router));

        // Direct Message button with plane icon
        add(new NavigationIconButton("resources/images/icons/dm.png", "messages", router));
    }

    /**
     * Customized panel for "For you" label and chevron icon
     * @return JPanel
     */
    private JPanel CustomizedPanel() {
        JPanel forYouPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));

        // "For you" label
        JLabel forYouLabel = new JLabel(
                "<html><span style='font-size:20pt; font-weight:600;'>For you</span></html>",
                SwingConstants.LEFT
        );
        forYouPanel.add(forYouLabel);

        // Chevron icon
        JLabel chevronLabel = new JLabel(new ScaledIcon("resources/images/icons/down-chevron.png", 16, 16));
        forYouPanel.add(chevronLabel, BorderLayout.SOUTH);
        return forYouPanel;
    }
}
