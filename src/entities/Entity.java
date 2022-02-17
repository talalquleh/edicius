package entities;
import java.awt.*;

import com.sun.source.doctree.EntityTree;
import helpers.CollisionBox;
import state.State;
import helpers.Position;
import helpers.Size;

/**
 * A class to identify all the entities of the game.
 */
public abstract class Entity {
    protected Position position;
    protected Size size;

    public Entity(){
        position = new Position(100, 100);
        size = new Size(75, 75);
    }

    public abstract void update(State state);

    public abstract CollisionBox getCollisionBox();
    public abstract boolean collidingWith(Entity other);

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
