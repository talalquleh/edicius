package src.helpers.buttons;

import src.state.GameMenu;
import src.gfx.Assets;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Button {
    public int x, y = 0;
    public Image img;
    public  int height,width=0;

    public Button( int x, int y, int width ,int height) {
        this.x = x;
        this.y = y;
        this.width=width;
        this.height=height;

    }

    /**
     * Set an image as a button background.
     * 
     * @param filePath
     */
    protected void setScaledImage(String filePath) {
        img = Assets.loadImage(filePath).getScaledInstance(100, 40, Image.SCALE_DEFAULT);
    }

    /**
     * Check whether a button is clicked or not.
     * 
     * @param x
     * @param y
     * @return whether a button is clicked or not.
     */
    public boolean clicked(int x, int y) {
        return (x >= this.x && x <= this.x + this.width) && (y >= this.y && y <= this.y + this.height);
    }

    /**
     * Load an image from a given path.
     * 
     * @param filePath
     * @return the loaded image.
     */
    protected Image loadImage(String filePath) {
        try {
            return ImageIO.read(new File(filePath));
        } catch (IOException ex) {
            Logger.getLogger(GameMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

