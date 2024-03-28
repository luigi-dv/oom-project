package src.presentation.components.messaging;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import src.domain.entities.messages.Chat;
import src.presentation.controllers.ChatInputController;
import src.presentation.interfaces.IMessageSentListener;
import src.presentation.interfaces.UIConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * A component that allows the user to input messages into a chat.
 */
public class ChatInputComponent extends JPanel {
    private final Chat chat;
    private final JTextField inputField;
    private final JButton sendButton;
    private final ChatInputController controller;

    private List<IMessageSentListener> messageSentListeners;

    /**
     * Creates a new ChatInputComponent.
     * @param chat The chat to send messages to.
     */
    public ChatInputComponent(Chat chat) {
        this.chat = chat;
        this.controller = new ChatInputController();
        this.messageSentListeners = new ArrayList<>();

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();

        inputPanel.add(inputField, BorderLayout.CENTER);

        setLayout(new BorderLayout());

        sendButton = createSendButton();

        inputPanel.add(sendButton, BorderLayout.EAST);
        setMinimumSize(new Dimension(UIConstants.WIDTH, 50));
        add(inputPanel, BorderLayout.SOUTH);
    }

    /**
     * Creates a send button that sends the message in the input field.
     * @return The send button.
     */
    private JButton createSendButton(){
        JButton button = new JButton("Send");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String messageContent = inputField.getText();
                if (!messageContent.trim().isEmpty()) {
                    try {
                        controller.sendMessage(chat.getId(), messageContent);
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    inputField.setText(""); // Clear the input field
                    for (IMessageSentListener listener : messageSentListeners) {
                        listener.onMessageSent(chat.getId());
                    }
                }
            }
        });

        return button;
    }

    /**
     * Adds a listener that is called when a message is sent.
     * @param listener The listener to add.
     */
    public void addMessageSentListener(IMessageSentListener listener) {
        this.messageSentListeners.add(listener);
    }
}