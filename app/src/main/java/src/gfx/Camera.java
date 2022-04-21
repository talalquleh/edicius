package src.gfx;

import src.entities.Entity;
import src.state.State;
import src.helpers.Position;
import src.helpers.Size;


import java.util.Optional;

public class Camera {

    private Position position;
    private Size windowSize;

    private Optional<Entity> objectWidthFocus;

    public Camera(Size windowSize) {
        this.position = new Position(0,0);
        this.windowSize = windowSize;

    }

    /**
     * To prevent having a null entity!
     * @param object
     */
    public void focusOn(Entity object){
        this.objectWidthFocus = Optional.of(object);
    }

    /**
     * Updating the position of our camera to focus on an entity!
     * @param state
     */
    public void update(State state){
        if(objectWidthFocus.isPresent()){
            Position objectPosition = objectWidthFocus.get().getPosition();
            this.position.setX(objectPosition.getX() - windowSize.getWidth() / 2);
            this.position.setY(objectPosition.getY() - windowSize.getHeight()/ 2);

            clampWithinBounds(state);
        }
    }

    /**
     * To make the camera focus only on the entity on the mid. of the screen.
     * @param state
     */
    private void clampWithinBounds(State state) {
        if(position.getX() < 0){
            position.setX(0);
        }
        if(position.getY() < 0){
            position.setY(0);
        }
        if(position.getX() + windowSize.getWidth() > state.getGameMap().getWidth()){
            position.setX(state.getGameMap().getWidth() - windowSize.getWidth());
        }
        if(position.getY() + windowSize.getHeight() > state.getGameMap().getHeight()){
            position.setY(state.getGameMap().getHeight() - windowSize.getHeight());
        }
    }

    public Position getPosition() {
        return position;
    }
}
