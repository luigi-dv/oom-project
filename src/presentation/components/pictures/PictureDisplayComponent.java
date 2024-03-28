package src.presentation.components.pictures;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.domain.entities.Picture;
import src.presentation.Router;

/**
 * A component that displays a picture in big.
 */
public class PictureDisplayComponent extends PictureComponent {

    /**
     * Creates a new PictureDisplayComponent.
     * @param router The router.
     * @param picture The picture.
     */
    public PictureDisplayComponent(Router router, Picture picture) {
        super(router, picture, false);
        initializePanel();

    }

    /**
     * Initializes the panel.
     */
    public void initializePanel() {
        JPanel imageContainerPanel = new JPanel(new BorderLayout());

        JLabel fullSizeImageLabel = createImageLabel(picture);

        JButton likeButton = createLikeButton(picture);
        likeButton.addActionListener(e -> {
            handleLikeAction(picture);
            refreshDisplayImage(picture); // Refresh the view
        });

        JPanel userPanel = createUserPanel(picture.getUser());
        JPanel infoPanel = createInfoPanel(picture, likeButton);

        imageContainerPanel.add(userPanel, BorderLayout.NORTH);
        imageContainerPanel.add(fullSizeImageLabel, BorderLayout.CENTER);
        imageContainerPanel.add(infoPanel, BorderLayout.SOUTH);

        add(imageContainerPanel, BorderLayout.CENTER);
        
    }
    
}
