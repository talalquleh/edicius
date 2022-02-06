package worlds;

import gfx.SpriteSheet;

import java.awt.*;

public class Tile {
    private Image sprite;
    public Tile(SpriteSheet spriteSheet){
        this.sprite = spriteSheet.getTiles("default");
    }
    public Image getSprite(){
        return sprite;
    }
}
