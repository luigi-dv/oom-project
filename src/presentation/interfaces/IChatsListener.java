package src.presentation.interfaces;

import javax.swing.*;
import java.util.UUID;

public interface IChatsListener extends IChatListener {
    /**
     * Display a chat in a panel.
     * @param panel The panel to display the chat in.
     * @param chatId The ID of the chat to display.
     */
    void displayChat(JPanel panel, UUID chatId);

}
