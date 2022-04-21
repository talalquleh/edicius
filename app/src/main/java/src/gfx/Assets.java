
package src.gfx;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Assets {
    /**
     * Loads an image from a specified path!
     * @param filePath
     * @return loadedImage or null
     */
    public static Image loadImage(String filePath) {
        try {
            return ImageIO.read(Assets.class.getResource(filePath));
        }catch(IOException e) {
            System.err.println("Couln't load image from path" + filePath);
        }
        return null;
    }
}
