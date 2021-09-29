package game.entities;
import java.awt.*;

import helper_classes.Size;

public abstract class Entity {
    protected Point  position;
    protected Size size;

    public Entity(){
        position = new Point();
        position.setLocation(50, 50);
        size = new Size(50, 50);
    }

    public abstract void update();

    public abstract Image getSprite();

    public Point getPosition(){
        return this.position;
    }

    public Size getSize(){
        return this.size;
    }

}
