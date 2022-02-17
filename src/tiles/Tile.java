package tiles;

import gfx.SpriteSheet;

import java.awt.*;

public class Tile {
    private Image floor;
    private Image wall;
    public Tile(SpriteSheet spriteSheet){
        this.floor = spriteSheet.getTiles("floor");
        this.wall = spriteSheet.getTiles("wall");
    }
    public Image getFloor(){
        return floor;
    }

    public Image getWall(){
        return wall;
    }
}
