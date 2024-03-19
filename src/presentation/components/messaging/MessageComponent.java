package src.presentation.components.messaging;


import javax.swing.*;
import java.awt.*;
import java.util.List;
import src.domain.entities.messages.Message;

public class MessageComponent extends JPanel {
    private JTextArea messageArea;

    public MessageComponent() {
        setLayout(new BorderLayout());
        messageArea = new JTextArea();
        messageArea.setEditable(false);
        JScrollPane messageScrollPane = new JScrollPane(messageArea);
        add(messageScrollPane, BorderLayout.CENTER);
    }

    // Method to update message display
    public void updateMessages(List<Message> messages) {
        StringBuilder sb = new StringBuilder();
        for (Message message : messages) {
            sb.append(message.getSender()).append(": ").append(message.getContent()).append("\n");
        }
        messageArea.setText(sb.toString());
    }

    // Method to add a new message
    public void addMessage(String sender, String content) {
        String currentText = messageArea.getText();
        String newText = currentText + sender + ": " + content + "\n";
        messageArea.setText(newText);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Message Component");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new MessageComponent());
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
