package src.controller;

import src.helpers.Position;

import java.awt.*;
import java.util.Map;

public class EnemyController implements Controller{

    private boolean up, right, down, left;

    @Override
    public boolean isRequestingUp() {
        return up;
    }

    @Override
    public boolean isRequestingRight() {
        return right;
    }

    @Override
    public Map<Boolean, Point> isMouseClicked() {
        return null;
    }

    @Override
    public boolean isRequestingDown() { return down; }

    @Override
    public boolean isRequestingLeft() {
        return left;
    }

    /**
     * Move an object of a given position to a target of a given position.
     * 
     * @param target
     * @param current
     */
    public void moveToTarget(Position target, Position current) {
        double deltaX = target.getX() - current.getX();
        double deltaY = target.getY() - current.getY();

        up    = deltaY < 0 && Math.abs(deltaY) > Position.PROXIMITY_RANGE;
        right = deltaX > 0 && Math.abs(deltaX) > Position.PROXIMITY_RANGE;
        down  = deltaY > 0 && Math.abs(deltaY) > Position.PROXIMITY_RANGE;
        left  = deltaX < 0 && Math.abs(deltaX) > Position.PROXIMITY_RANGE;
    }

    /**
     * Stop and object which has this controller from moving.
     * 
     */
    public void stop() {
        up    = false;
        right = false;
        down  = false;
        left  = false;
    }
}
