package tiles;

import gfx.SpriteSheet;

import java.awt.*;

public class Tile {
    private Image sprite;
    public Tile(SpriteSheet spriteSheet){
        this.sprite = spriteSheet.getTiles("floor");
    }
    public Image getSprite(){
        return sprite;
    }
}
