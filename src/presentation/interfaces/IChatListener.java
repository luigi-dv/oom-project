package src.presentation.interfaces;

import javax.swing.*;
import java.util.UUID;

/**
 * A listener for chat events.
 */
public interface IChatListener {
    /**
     * Called when a new message is received in a chat.
     * @param chatId
     */
    void onNewMessage(UUID chatId);
}
