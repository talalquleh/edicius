package entities;


import java.awt.Image;

import controller.Controller;
import state.State;
import gfx.Animation;
import gfx.SpriteSheet;
import helpers.Direction;
import helpers.Motion;

public abstract class MovingEntity extends Entity {

    protected Controller controller;
    protected Motion motion;
    protected Animation animation;
    protected Direction direction;

    public MovingEntity(Controller controller , SpriteSheet spriteLibrary) {
        super();
        this.controller = controller;
        this.motion = new Motion(2);
        this.animation = new Animation(spriteLibrary.getUnit("dave"));
        this.direction = Direction.S;
    }
    @Override
    public void update(State state) { // focus here
        motion.update(controller);
        position.apply(motion);
        manageDirection();
        decideAnimation();
        animation.update(direction);
    }

    private void decideAnimation() {
        if(motion.isMoving()){
            animation.playAnimation("walk");
        }else{
            animation.playAnimation("stand");
        }
    }

    private void manageDirection(){
        if(motion.isMoving()){
            this.direction = Direction.fromMotion(motion);
        }
    }

    @Override
    public Image getSprite() {
        return animation.getSprite();
    }

    public Controller getController(){
        return this.controller;
    }
}
