package entities;

import gfx.Camera;
import helpers.CollisionBox;
import helpers.Position;
import state.State;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class Shot {


    private int x;
    private int y;
    private Image shotImage;
    private Point target;

    public Shot(Point target, Player player) {
        this.x = (int) player.getPosition().getX();
        this.y = (int) player.getPosition().getY();
        this.target = target;

        try {
            this.shotImage = ImageIO.read(new File("res/sprites/fireShots/fireBall.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Point getPosition(){
        return new Point(x, y);
    }

    public void setPosition(double  x, double  y){
        this.x = (int) x;
        this.y = (int) y;
    }

    public void shoot(Graphics graphics, Camera camera){
        this.shotGoing();
        graphics.drawImage(this.shotImage,
                this.x - camera.getPosition().intX() - 15,
                this.y - camera.getPosition().intY() - 15, 15, 15, null);
        //System.out.println(this.x + " | " + this.y);

    }

    public void shotGoing(){
        this.setPosition(this.x + (this.target.x - this.x) * 0.02, this.y + (this.target.y - this.y) * 0.02);
    }

}
