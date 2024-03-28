package src.presentation.interfaces;

import java.util.UUID;

public interface IChatsListener {
    /**
     * Display a chat in a panel.
     * @param chatId The ID of the chat to display.
     */
    void displayChat(UUID chatId);
}
