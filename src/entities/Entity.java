package entities;
import java.awt.*;

import game.state.State;
import helper_classes.Position;
import helper_classes.Size;

public abstract class Entity {
    protected Position position;
    protected Size size;

    public Entity(){
        position = new Position(50, 50);
        size = new Size(50, 50);
    }

    public abstract void update(State state);

    public abstract Image getSprite();

    public Position getPosition(){
        return this.position;
    }

    public Size getSize(){
        return this.size;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

}