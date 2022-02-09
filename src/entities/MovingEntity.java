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

    /**
     * Initilize all the components that are going to be used to update an entity!
     * @param controller
     * @param spriteLibrary
     */
    public MovingEntity(Controller controller , SpriteSheet spriteLibrary) {
        super();
        this.controller = controller;
        this.motion = new Motion(2);
        this.animation = new Animation(spriteLibrary.getUnit("dave")); // from here we can change the player by entering the other player name (matt)!
        this.direction = Direction.S;
    }

    /**
     * Updates and manages the motions and the directions of the player!
     * @param state
     */
    @Override
    public void update(State state) { // focus here
        motion.update(controller);
        position.apply(motion);
        manageDirection();
        decideAnimation();
        animation.update(direction);
    }

    /**
     * Decide what will the animations of the player be according to the playAnimation function which was implemented on the Animation class!
     */
    private void decideAnimation() {
        if(motion.isMoving()){
            animation.playAnimation("walk");
        }else{
            animation.playAnimation("stand");
        }
    }

    /**
     * Decide what will the direction of the player be according to the fromMotion function which was implemented on the Direction class!
     */
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
