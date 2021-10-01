package helper_classes;

public class Vector2D {
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
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
        return Math.sqrt((x * x) +( y * y));
    }

    public void normlize() {
        double length = this.length();
        if(x == 0) {
            x = 0;
        }else {
            x = x /length ;
        }
        if(y == 0) {
            y = 0;
        }else {
            y = y/length ;
        }
    }
}
