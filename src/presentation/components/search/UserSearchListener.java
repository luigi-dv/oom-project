package src.presentation.components.search;

import javax.swing.JPanel;

import src.domain.entities.User;

public interface UserSearchListener {
    void displayUserDetails(JPanel panel, User user);
}
