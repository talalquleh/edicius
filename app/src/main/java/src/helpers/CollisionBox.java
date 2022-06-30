package src.helpers;

import java.awt.*;

public class CollisionBox {

    private Rectangle bounds;

    public CollisionBox(Rectangle bounds){
        this.bounds = bounds;
    }

    /**
     * Check whether the current collision box collides with another.
     * 
     * @param other
     * @return whether the current collision box collides with another.
     */
    public boolean collidesWith(CollisionBox other){
        return bounds.intersects(other.getBounds());
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
