
package src.presentation.components.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * Represents a custom Swing ImageIcon with an SVG icon loaded from a specified file path.
 * The icon is automatically scaled to a predefined size defined in UIConstants.
 */
public class ScaledIcon extends ImageIcon {

    /**
     * Constructs a ScaledIcon with the specified icon path.
     *
     * @param iconPath The file path of the icon to load.
     */
    public ScaledIcon(String iconPath, int width, int height) {
        super(Objects.requireNonNull(scaledImage(iconPath, width, height)));
    }

    /**
     * Loads the image from the specified file path, scales it, and returns the scaled image.
     *
     * @param iconPath The file path of the icon to load.
     * @return The scaled BufferedImage.
     */
    private static BufferedImage scaledImage(String iconPath, int width, int height) {
        try {
            // Load the image
            File file = new File(iconPath);
            BufferedImage originalImage = ImageIO.read(file);

            // Scale the image
            BufferedImage scaledImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = scaledImage.createGraphics();

            // Set rendering hints for better quality
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

            g2d.drawImage(originalImage, 0, 0, width, height, null);
            g2d.dispose();

            return scaledImage;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
