package src.presentation.components.buttons;


import java.awt.*;
import java.util.Objects;
import javax.swing.*;

public class ButtonComponent extends JButton {

    /**
     * Constructor for the ButtonComponent class.
     * @param text The text to display on the button.
     * @param fontSize The font size of the text.
     * @param padding The padding of the button.
     * @param alignmentX The alignment of the button.
     * @param type The type of the button.
     * @param darkMode The dark mode status.
     */
    public ButtonComponent(String text, int fontSize, int padding, float alignmentX, String type, boolean darkMode) {
        super(text);
        this.setAlignmentX(alignmentX);
        this.setFont(new Font("Arial", Font.BOLD, fontSize));

        // Set maximum size to unlimited horizontally
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, this.getPreferredSize().height));

        this.setOpaque(true);
        this.setBorderPainted(false);
        // Set the button color based on type and dark mode
        this.setBackground(getButtonColor(type, darkMode));
        this.setForeground(getTextColor(type, darkMode));

        // Set the rounded border
        this.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding)); // Padding
    }

    /**
     * Method to get the button color based on type and dark mode
     * @param type The type of the button
     * @param darkMode The dark mode status
     * @return The button color
     */
    private Color getButtonColor(String type, boolean darkMode) {
            return switch (type) {
                case "primary" -> darkMode ? new Color(21, 101, 192) : new Color(56, 151, 240);
                case "secondary" -> darkMode ? new Color(66, 66, 66) : new Color(225, 228, 232);
                case "danger" -> darkMode ? new Color(211, 47, 47) : new Color(220, 53, 69);
                default -> darkMode ? new Color(66, 66, 66) : new Color(225, 228, 232);
            };
    }

    /**
     * Method to get the text color based on type and dark mode
     * @param type The type of the button
     * @param darkMode The dark mode status
     * @return The text color
     */
    private Color getTextColor(String type, boolean darkMode) {
        return darkMode || Objects.equals(type, "primary") || Objects.equals(type, "danger") ? Color.WHITE : Color.BLACK;
    }
}
