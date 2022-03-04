package entities;

import controller.Controller;
import gfx.SpriteSheet;
import helpers.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Shot extends MovingEntity {

    Image shotImage;
    public Shot(Controller controller, SpriteSheet spriteSheet) {
        super(controller, spriteSheet);
        try {
            this.shotImage = ImageIO.read(new File("res/sprites/fireShots/fireBall.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void handleCollision(Entity other) {

    }

    public void shoot(Position target){
        if(target.getY() >= this.getPosition().getY() &&
                target.getX() >= this.getPosition().getX()){
            setPosition(
                    new Position( this.getPosition().getX() + 2,
                             this.getPosition().getY() + 2)
            );
        }
        if(target.getY() <= this.getPosition().getY() &&
                target.getX() >= this.getPosition().getX()){
            setPosition(
                    new Position( getPosition().getX() + 2,
                             getPosition().getY() - 2)
            );
        }
        if(target.getY() >= this.getPosition().getY() &&
                target.getX() <= this.getPosition().getX()){
            setPosition(
                    new Position(this.getPosition().getX() - 2,
                             this.getPosition().getY() + 2)
            );
        }
        if(target.getY() <= this.getPosition().getY() &&
                target.getX() <= this.getPosition().getX()){
            setPosition(
                    new Position(this.getPosition().getX() - 2,
                            this.getPosition().getY() - 2)
            );
        }
    }

    public Image getShotImage() {
        return shotImage;
    }
}
