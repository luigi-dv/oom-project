package src.presentation.interfaces;

import javax.swing.JPanel;

import src.domain.entities.User;

public interface IUserSearchListener {
    void displayUserDetails(JPanel panel, User user);
}
