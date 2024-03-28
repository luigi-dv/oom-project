package src.presentation.components.messaging;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import src.domain.entities.User;
import src.domain.entities.messages.Message;

public class ChatComponent extends JPanel {
    private final User user;
    private final JTextField searchField;
    private final MessageComponent messageComponent;
    private final JTextField inputField;
    private final JButton sendButton;

    public ChatComponent(User user) {
        // The user is needed to display the info of the user the current user is chatting with.
        this.user = user;
        setLayout(new BorderLayout());

        // Search panel
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchField = new JTextField();
        searchPanel.add(searchField, BorderLayout.CENTER);
        add(searchPanel, BorderLayout.NORTH);

        // Message display panel
        messageComponent = new MessageComponent();
        JScrollPane messageScrollPane = new JScrollPane(messageComponent);
        add(messageScrollPane, BorderLayout.CENTER);

        // Input panel
        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        inputPanel.add(inputField, BorderLayout.CENTER);
        sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = inputField.getText();
                // Process the message, e.g., send it
                messageComponent.addMessage("You", message);
                inputField.setText(""); // Clear the input field
            }
        });
        inputPanel.add(sendButton, BorderLayout.EAST);
        add(inputPanel, BorderLayout.SOUTH);
    }

    // Method to update message display
    public void updateMessages(List<Message> messages) {
        messageComponent.updateMessages(messages);
    }
}
