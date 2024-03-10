package src.application.views.interfaces;

import javax.swing.*;

import src.domain.entities.Picture;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.util.List;

public interface IExploreUI {

    static JScrollPane createScrollPane(JPanel imageGridPanel) {
        JScrollPane scrollPane = new JScrollPane(imageGridPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        return scrollPane;
    }

    static JPanel createImageGridPanel(List<Picture> pictures, MouseAdapter mouseAdapter, int imageSize) {
        JPanel imageGridPanel = new JPanel(new GridLayout(0, 3, 2, 2)); // 3 columns, auto rows

        for (Picture picture : pictures) {
            File imageFile = new File(picture.getImagePath());
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(imageFile.getPath()).getImage()
                    .getScaledInstance(imageSize, imageSize, Image.SCALE_SMOOTH));
            JLabel imageLabel = new JLabel(imageIcon);
            imageLabel.setName(picture.getId().toString());
            imageLabel.addMouseListener(mouseAdapter);
            imageGridPanel.add(imageLabel);
        }
        return imageGridPanel;
    }

    static JPanel createSearchPanel() {
        JPanel searchPanel = new JPanel(new BorderLayout());
        JTextField searchField = new JTextField(" Search Users");
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, searchField.getPreferredSize().height)); // Limit
        return searchPanel;
    }
}
