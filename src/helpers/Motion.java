package helpers;

import controller.Controller;

public class Motion {
    private Vector2D vector;
    private double speed;

    public Motion(double speed) {
        this.vector = new Vector2D(0,0);
        this.speed = speed;
    }

    /**
     * Updating the position of the moving entity, according to the controller provided by this moving entity!
     * @param controller
     */
    public void update(Controller controller ) {
        int deltaX = 0 , deltaY = 0;

        if(controller.isRequestingUp()) {
            deltaY--;
        }
        if(controller.isRequestingDown()) {
            deltaY++;
        }
        if(controller.isRequestingLeft()) {
            deltaX--;
        }
        if(controller.isRequestingRigth()) {
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
}
