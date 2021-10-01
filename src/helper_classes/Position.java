package helper_classes;

public class Position {
    private double x;
    private double y;

    public Position(double x, double y) {
        super();
        this.y = y;
        this.x = x;
    }

    public int intX(){return (int) Math.round(x);}
    public int intY(){return (int) Math.round(y);}

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }

    public void apply(Motion motion){
        Vector2D vector = motion.getVector();
        x += vector.getX();
        y += vector.getY();
    }
}
