package src.presentation.interfaces;

import javax.swing.*;

import src.domain.entities.Picture;
import src.domain.entities.User;
import src.presentation.components.search.UserSearchComponent;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.util.List;

public interface IExploreUI {

    static JScrollPane createScrollPane(JPanel imageGridPanel) {
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.add(imageGridPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        return scrollPane;
    }

    static JPanel createImageGridPanel(List<Picture> pictures, MouseAdapter mouseAdapter, int imageSize) {
        JPanel imageGridPanel = new JPanel(new GridLayout(0, 3, 2, 2)); // 3 columns, auto rows
        for (Picture picture : pictures) {
            String filePath = "resources/storage/uploaded/" + picture.getImagePath();
            File imageFile = new File(filePath);
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(imageFile.getPath()).getImage()
                    .getScaledInstance(imageSize, imageSize, Image.SCALE_SMOOTH));
            JLabel imageLabel = new JLabel(imageIcon);
            imageLabel.setName(picture.getId().toString());
            imageLabel.addMouseListener(mouseAdapter);
            imageGridPanel.add(imageLabel);
        }
        return imageGridPanel;
    }

    static JPanel createUserGridPanel(List<User> users, MouseAdapter mouseAdapter, int imageSize) {
        JPanel userGridPanel = new JPanel(new GridLayout()); // 3 columns, auto rows

        for (User user : users) {
            JPanel userPanel = new UserSearchComponent(user, mouseAdapter);
            userPanel.addMouseListener(mouseAdapter);
            userGridPanel.add(userPanel);
        }

        return userGridPanel;
    }
}
