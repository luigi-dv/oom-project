package src.presentation.components.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AvatarImagePanel extends JPanel {
    private BufferedImage image;
    private final int width;
    private final int height;

    /**
     * Constructs an AvatarIcon with the specified image path, width, and height.
     * @param imagePath The file path of the image to load.
     * @param width The width of the icon.
     * @param height The height of the icon.
     */
    public AvatarImagePanel(String imagePath, int width, int height) {
        this.width = width;
        this.height = height;
        // System.out.println(imagePath);
        try {
            this.image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setOpaque(false);
        setPreferredSize(new Dimension(this.width, this.height));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        int diameter = Math.min(getWidth(), getHeight());
        float radius = diameter / 2f;

        // Create a circular clip
        Ellipse2D circle = new Ellipse2D.Float(getWidth() / 2f - radius, getHeight() / 2f - radius, diameter, diameter);
        g2d.setClip(circle);

        // Draw the image
        g2d.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        g2d.dispose();
    }
}
