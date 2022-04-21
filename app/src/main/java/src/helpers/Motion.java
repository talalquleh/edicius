package src.helpers;

import src.controller.Controller;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Motion {
    private Vector2D vector;
    List<Vector2D> shotsVector;
    private double speed;
    private Map<Boolean, Point> playerShots;

    public Motion(double speed) {
        this.vector = new Vector2D(0,0);
        this.speed = speed;
        playerShots = new HashMap<>();
        shotsVector = new ArrayList<>();
    }

    /**
     * Updating the position of the moving entity, according to the controller provided by this moving entity!
     * @param controller
     */
    public void update(Controller controller ) {
        int deltaX = 0 , deltaY = 0;
        playerShots = controller.isMouseClicked();

        if(controller.isRequestingUp()) {
            deltaY--;
        }
        if(controller.isRequestingDown()) {
            deltaY++;
        }
        if(controller.isRequestingLeft()) {
            deltaX--;
        }
        if(controller.isRequestingRight()) {
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
