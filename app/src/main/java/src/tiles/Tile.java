package src.tiles;

import src.gfx.SpriteSheet;

import java.awt.*;

public class Tile {
    private Image floor;
    private Image wall;
    public static Image cursor;
    public Tile(SpriteSheet spriteSheet){
        this.floor = spriteSheet.getTiles("floor");
        this.wall = spriteSheet.getTiles("wall");
        cursor = spriteSheet.getTiles("cursor");
    }
    public Image getFloor(){
        return floor;
    }

    public Image getWall(){
        return wall;
    }
}
