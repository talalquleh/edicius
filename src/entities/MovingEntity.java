package entities;


import java.awt.Image;

import controller.Controller;
import entities.Entity;
import game.state.State;
import gfx.Animation;
import gfx.SpriteSheet;
import helper_classes.Direction;
import helper_classes.Motion;

public abstract class MovingEntity extends Entity {

    protected Controller controller;
    protected Motion motion;
    protected Animation animationManager;
    protected Direction direction;

    public MovingEntity(Controller controller , SpriteSheet spriteLibrary) {
        super();
        this.controller = controller;
        this.motion = new Motion(2);
        this.animationManager = new Animation(spriteLibrary.getUnit("dave"));
        this.direction = Direction.S;
    }
    @Override
    public void update(State state) { // focus here
        motion.update(controller);
        position.apply(motion);
        manageDirection();
        decideAnimation();
        animationManager.update(direction);
    }

    private void decideAnimation() {
        if(motion.isMoving()){
            animationManager.playAnimation("walk");
        }else{
            animationManager.playAnimation("stand");
        }
    }

    private void manageDirection(){
        if(motion.isMoving()){
            this.direction = Direction.fromMotion(motion);
        }
    }

    @Override
    public Image getSprite() {
        return animationManager.getSprite();
    }

    public Controller getController(){
        return this.controller;
    }
}
