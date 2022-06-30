package src.helpers;

public class Position {

    public static int PROXIMITY_RANGE = 1;

    private double x;
    private double y;


    public Position(double x, double y) {
        super();
        this.x = x;
        this.y = y;
    }

    public int intX() {
        return (int) Math.round(x);
    }

    public int intY() {
        return (int) Math.round(y);
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    /**
     * Sets a motion for some vector position!
     * @param motion
     */
    public void apply(Motion motion) {
        Vector2D vector = motion.getVector();
        x += vector.getX();
        y += vector.getY();
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    /**
     * Check whether a given position is in the range of the current position or not.
     * 
     * @param position
     * @return whether a given position is in the range of the current position or not.
     */
    public boolean isInRangeOf(Position position) {
        return 		(Math.abs(x -position.getX()) < Position.PROXIMITY_RANGE)
                && 	(Math.abs(y -position.getY()) < Position.PROXIMITY_RANGE);
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == null || !(other instanceof Position)) return false;
        return ((Position)other).getX() == x && ((Position)other).getY() == y;
    }

    @Override
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
}
