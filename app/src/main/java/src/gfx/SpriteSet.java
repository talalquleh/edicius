package src.gfx;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

public class SpriteSet {

    private Map<String, Image >animationSheets;

    public SpriteSet() {
        this.animationSheets = new HashMap<>();
    }

    /**
     * Add a sheet from an image to the animation sheets.
     * @param name
     * @param animationSheet
     */
    public void addSheets(String name , Image animationSheet)  {
        animationSheets.put(name, animationSheet);
    }

    public Image get(String name) {
        return animationSheets.get(name);
    }

}
