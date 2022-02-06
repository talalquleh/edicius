
package gfx;

import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Assets {
    public static Image loadImage(String filePath) {
        try {
            return ImageIO.read(Assets.class.getResource(filePath));
        }catch(IOException e) {
            System.err.println("Couln't load image from path" + filePath);
        }
        return null;
    }
}
