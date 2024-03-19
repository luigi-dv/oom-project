package src.presentation.components.messaging;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import src.domain.entities.messages.Message;

public class ChatComponent extends JPanel {
    private JTextField searchField;
    private MessageComponent messageComponent;
    private JTextField inputField;
    private JButton sendButton;

    public ChatComponent() {
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Chat Component");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new ChatComponent());
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
