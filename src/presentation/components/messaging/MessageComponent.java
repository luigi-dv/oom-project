package src.presentation.components.messaging;


import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import src.domain.entities.messages.Message;
import src.presentation.interfaces.UIConstants;

public class MessageComponent extends JPanel {
    private final JLabel messageLabel;
    private final boolean isOwnMessage;

    public MessageComponent(Message message, boolean isOwnMessage) {
        setLayout(new BorderLayout());
        this.isOwnMessage = isOwnMessage;
        // Create a JLabel to display the message content
        messageLabel = new JLabel(message.getContent());
        messageLabel.setMaximumSize(new Dimension(UIConstants.WIDTH - 50, Integer.MAX_VALUE));
        setMessage();
    }

    /**
     * Set the message style based on whether it is the user's own message or not.
     */
    public void setMessage() {
        if (isOwnMessage) {
            setBackground(new Color(14, 122, 254));
            messageLabel.setBackground(new Color(14, 122, 254));
            messageLabel.setForeground(Color.WHITE);
            setBorder(new CompoundBorder(new LineBorder(new Color(255, 255, 255), 1, true), new EmptyBorder(5, 5, 5, 5)));
        } else {
            setBackground(new Color(216,216,216));
            messageLabel.setBackground(new Color(216,216,216));
            messageLabel.setForeground(Color.BLACK);
            setBorder(new CompoundBorder(new LineBorder(new Color(255, 255, 255), 1, true), new EmptyBorder(5, 5, 5, 5)));
        }
        // Add the JLabel to the MessageComponent
        add(messageLabel, BorderLayout.CENTER);
    }
}
