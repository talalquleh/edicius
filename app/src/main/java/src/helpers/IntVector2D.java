package src.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IntVector2D {
    private int x;
    private int y;

    public IntVector2D(int x, int y) {
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

    public void multiply(int speed) {
        x *= speed;
        y *= speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double length() {
        return Math.sqrt((x * x) + (y * y));
    }


    /**
     * Get the distance between 2 vectors.
     * 
     * @param tile
     * @param goal
     * @return double value about the distance.
     */
    public static double getDistance(IntVector2D tile, IntVector2D goal) {
        return Math.sqrt(Math.pow(tile.getX() - goal.getX(), 2) + Math.pow(tile.getY() - goal.getY(), 2));
    }

    /**
     * Getting the Manhattan Distance of 2 vectors.
     * To read more about Manhattan Distance please read https://iq.opengenus.org/manhattan-distance/
     * 
     * @param tile
     * @param goal
     * @return Manhattan Distance of 2 vectors.
     */
    public static double getManhattanDistance(IntVector2D tile, IntVector2D goal)
    {
        return Math.abs(tile.getX() - goal.getX()) + Math.abs(tile.getY() - goal.getY());
    }

    /**
     * Getting the Octile Distance of 2 vectors.
     * the octile heuristic is max(x, y) + (sqrt(2)-1)*min(x, y)
     * 
     * @param tile
     * @param goal
     * @return Octile Distance of 2 vectors.
     */
    public static double getOctileDistance(IntVector2D tile, IntVector2D goal)
    {
        double C = 1, D = Math.sqrt(2);
        double E = 2 * C - D;
        double deltax = Math.abs(tile.x-goal.x);
        double deltay = Math.abs(tile.y-goal.y);
        return (E * (deltax - deltay) + D * (deltax + deltay)) / 2;
    }

    /**
     * Get the neighbors of 2 points.
     * 
     * @param maxx
     * @param maxy
     * @return List of vectors that are neighbors of 2 points.
     */
    public List<IntVector2D> getNeighbors(int maxx, int maxy)
    {
        List<IntVector2D> l = new ArrayList<>();
        if (x != 0) l.add(new IntVector2D(x-1, y));
        if (x != maxx-1) l.add(new IntVector2D(x+1, y));
        if (y != 0) l.add(new IntVector2D(x, y-1));
        if (y != maxy-1) l.add(new IntVector2D(x, y+1));

        if (x != 0 && y != 0) l.add(new IntVector2D(x-1, y-1));
        if (x != maxx-1 && y != maxy-1) l.add(new IntVector2D(x+1, y+1));
        if (x != 0 && y != maxy-1) l.add(new IntVector2D(x-1, y+1));
        if (x != maxx-1 && y != 0) l.add(new IntVector2D(x+1, y-1));
        return l;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof IntVector2D)) return false;
        return x == ((IntVector2D)other).x && y == ((IntVector2D)other).y;
    }
    public String toString() { return "X: " + x + " Y: " + y; }
}
