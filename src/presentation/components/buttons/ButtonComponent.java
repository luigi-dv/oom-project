package src.presentation.components.buttons;

import javax.swing.*;
import java.awt.*;

public class ButtonComponent extends JButton {
    public ButtonComponent(String text, int fontSize, float alignmentX, String type, boolean darkMode) {
        super(text);
        this.setAlignmentX(alignmentX);
        this.setFont(new Font("Arial", Font.BOLD, fontSize));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, this.getMinimumSize().height));
        this.setOpaque(true);
        this.setBorderPainted(false);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add some padding

        if (darkMode) {
            this.setForeground(Color.WHITE);
            switch (type) {
                case "primary":
                    this.setBackground(new Color(21, 101, 192));
                    break;
                case "secondary":
                    this.setBackground(new Color(66, 66, 66));
                    break;
                case "danger":
                    this.setBackground(new Color(211, 47, 47));
                    break;
                default:
                    this.setBackground(new Color(66, 66, 66));
                    break;
            }
        } else {
            switch (type) {
                case "primary":
                    this.setBackground(new Color(56, 151, 240));
                    this.setForeground(Color.WHITE);
                    break;
                case "secondary":
                    this.setBackground(new Color(225, 228, 232));
                    this.setForeground(Color.BLACK);
                    break;
                case "danger":
                    this.setBackground(new Color(220, 53, 69));
                    this.setForeground(Color.WHITE);
                    break;
                default:
                    this.setBackground(new Color(225, 228, 232));
                    this.setForeground(Color.BLACK);
                    break;
            }
        }
    }
}
