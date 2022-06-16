package src.helpers;

import src.controller.Controller;

import java.util.ArrayList;
import java.util.List;

public class Motion {
    private Vector2D vector;
    List<Vector2D> shotsVector;
    private double speed;
    // private Map<Boolean, Point> playerShots;

    public Motion(double speed) {
        this.vector = new Vector2D(0,0);
        this.speed = speed;
        // playerShots = new HashMap<>();
        shotsVector = new ArrayList<>();
    }

    /**
     * Updating the position of the moving entity, according to the controller provided by this moving entity!
     * @param controller
     */
    public void update(Controller controller, boolean horizontal, boolean vertical ) {
        int deltaX = 0 , deltaY = 0;
        // playerShots = controller.isMouseClicked();

        if(controller.isRequestingUp() && vertical) {
            deltaY--;
        }
        if(controller.isRequestingDown() && vertical) {
            deltaY++;
        }
        if(controller.isRequestingLeft() && horizontal) {
            deltaX--;
        }
        if(controller.isRequestingRight() && horizontal) {
            deltaX++;
        }
        this.vector = new Vector2D(deltaX , deltaY);
        this.vector.normlize();
        this.vector.multiply(speed);
    }

    public Vector2D getVector() {
        return vector;
    }

    public boolean isMoving() {
        return vector.length() > 0;
    }

    public void stop(){
        vector = new Vector2D(0, 0);
    }
    public void setMotion(double speed){
        this.vector = new Vector2D(0,0);
        this.speed = speed;
    }
    public double getSpeed(){return this.speed;}

}
