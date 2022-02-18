package controller;

import helpers.Position;

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
    public boolean isRequestingDown() { return down; }

    @Override
    public boolean isRequestingLeft() {
        return left;
    }

    public void moveToTarget(Position target, Position current) {
        double deltaX = target.getX() - current.getX();
        double deltaY = target.getY() - current.getY();

        up    = deltaY < 0 && Math.abs(deltaY) > Position.PROXIMITY_RANGE;
        right = deltaX > 0 && Math.abs(deltaX) > Position.PROXIMITY_RANGE;
        down  = deltaY > 0 && Math.abs(deltaY) > Position.PROXIMITY_RANGE;
        left  = deltaX < 0 && Math.abs(deltaX) > Position.PROXIMITY_RANGE;
    }

    public void stop() {
        up    = false;
        right = false;
        down  = false;
        left  = false;
    }
}
