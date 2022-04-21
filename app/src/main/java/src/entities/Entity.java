package src.entities;
import java.awt.*;

import com.sun.source.doctree.EntityTree;
import src.helpers.CollisionBox;
import src.state.State;
import src.helpers.Position;
import src.helpers.Size;

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
