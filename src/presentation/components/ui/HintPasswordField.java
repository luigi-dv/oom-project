package src.presentation.components.ui;

import javax.swing.*;
import java.awt.*;

/**
 * The HintPasswordField class represents a password field with a hint.
 * Inspired by StackOverflow Post <a href="https://stackoverflow.com/questions/1738966/java-jtextfield-with-input-hint">...</a>
 */
public class HintPasswordField extends JPasswordField {
    public HintPasswordField(String hint) {
        _hint = hint;
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (getText().isEmpty()) {
            int h = getHeight();
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Insets ins = getInsets();
            FontMetrics fm = g.getFontMetrics();
            int c0 = getBackground().getRGB();
            int c1 = getForeground().getRGB();
            int m = 0xfefefefe;
            int c2 = ((c0 & m) >>> 1) + ((c1 & m) >>> 1);
            g.setColor(new Color(c2, true));
            g.drawString(_hint, ins.left, h / 2 + fm.getAscent() / 2 - 2);
        }
    }
    private final String _hint;
}