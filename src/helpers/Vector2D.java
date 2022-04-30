package helpers;

import java.util.*;

public class Vector2D {
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void multiply(double speed) {
        x *= speed;
        y *= speed;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double length() {
        return Math.sqrt((x * x) + (y * y));
    }


    public static double getDistance(Vector2D tile, Vector2D goal) {
        return Math.sqrt(Math.pow(tile.getX() - goal.getX(), 2) + Math.pow(tile.getY() - goal.getY(), 2));
    }
    public static double getManhattanDistance(Vector2D tile, Vector2D goal)
    {
        return Math.abs(tile.getX() - goal.getX()) + Math.abs(tile.getY() - goal.getY());
    }

    public List<Vector2D> getNeighbors(int maxx, int maxy)
    {
        List<Vector2D> l = new ArrayList<>();
        if (x != 0) l.add(new Vector2D(x-1, y));
        if (x != maxx-1) l.add(new Vector2D(x+1, y));
        if (y != 0) l.add(new Vector2D(x, y-1));
        if (y != maxy-1) l.add(new Vector2D(x, y+1));

        if (x != 0 && y != 0) l.add(new Vector2D(x-1, y-1));
        if (x != maxx-1 && y != maxy-1) l.add(new Vector2D(x+1, y+1));
        if (x != 0 && y != maxy-1) l.add(new Vector2D(x-1, y+1));
        if (x != maxx-1 && y != 0) l.add(new Vector2D(x+1, y-1));
        return l;
    }

    /**
     * To separates the direction from the magnitude!
     */
    public void normalize() {
        double length = this.length();
        if (x == 0) {
            x = 0;
        } else {
            x = x / length;
        }
        if (y == 0) {
            y = 0;
        } else {
            y = y / length;
        }
    }
}

