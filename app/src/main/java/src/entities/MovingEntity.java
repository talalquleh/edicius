package src.entities;


import java.awt.*;
import java.util.ArrayList;

import src.controller.Controller;
import src.game.Game;
import src.helpers.*;
import src.state.State;
import src.gfx.Animation;
import src.gfx.SpriteSheet;

import java.util.Iterator;
import java.util.List;

public abstract class MovingEntity extends Entity {

    protected Controller controller;
    protected Motion motion;
    protected Animation animation;
    protected Direction direction;
    protected Size collisionBoxSize;
    protected State state;

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
        this.collisionBoxSize = new Size(16, 28);
    }

    /**
     * Updates and manages the motions and the directions of the player!
     * @param state
     */
    @Override
    public void update(State state) { // focus here
        this.state = state;
        Position lastPosition = new Position(position.getX(), position.getY());
        motion.update(controller, true, true);
        position.apply(motion);
        manageDirection();
        Position checkPosition = new Position(position.getX(), position.getY());
        handleCollisions(state);
        if (!checkPosition.equals(position)) {
            position = new Position(lastPosition.getX(), lastPosition.getY());
            if (motion.getVector().getX() != 0 && motion.getVector().getY() != 0) {
                motion.update(controller, true, false);
                position.apply(motion);
                checkPosition = new Position(position.getX(), position.getY());
                handleCollisions(state);
                if (!checkPosition.equals(position)) {
                    position = new Position(lastPosition.getX(), lastPosition.getY());
                    motion.update(controller, false, true);
                    position.apply(motion);
                    checkPosition = new Position(position.getX(), position.getY());
                    handleCollisions(state);
                    if (!checkPosition.equals(position)) {
                        position = new Position(lastPosition.getX(), lastPosition.getY());
                    } else manageDirection();
                } else manageDirection();
            }
        }
        decideAnimation();
        animation.update(direction);
    }

    protected void handleCollisions(State state){
        state.getCollidingEntities(this).forEach(this::handleCollision);
    }

    protected abstract void handleCollision(Entity other);

    @Override
    public CollisionBox getCollisionBox() {
        return new CollisionBox(
                new Rectangle(
                        position.intX() - (int) collisionBoxSize.getWidth()/2  -2,
                        position.intY() - (int) collisionBoxSize.getHeight()/2,
                    (int) collisionBoxSize.getWidth(),
                    (int) collisionBoxSize.getHeight()
        )) ;
    }

    @Override
    public boolean collidingWith(Entity other) {
        return getCollisionBox().collidesWith(other.getCollisionBox());
    }

    protected List<CollisionBox> getMapCollisionBoxes(){
        List<CollisionBox> mcb = new ArrayList<CollisionBox>();
        for (int i = 0; i < this.state.getMap("map1.txt").length ; i++) {
            for (int j = 0; j < this.state.getMap("map1.txt").length; j++) {
                if(this.state.getMap("map1.txt")[i][j] == 1){
                    mcb.add( new CollisionBox(
                            new Rectangle(Game.SPRITE_SIZE * i , Game.SPRITE_SIZE * j , Game.SPRITE_SIZE, Game.SPRITE_SIZE))
                    );
                }
            }
        }
        return mcb;
    }

    protected void handleWallCollision(double speed){
        switch (this.direction){
            case S:
                this.position.setY(this.position.getY() - speed);
                break;
            case E:
                this.position.setX(this.position.getX() - speed);
                break;
            case N:
                this.position.setY(this.position.getY() + speed);
                break;
            case W:
                this.position.setX(this.position.getX() + speed);
                break;
            case NE:
                this.position.setX(this.position.getX() - speed / 1.4);
                this.position.setY(this.position.getY() + speed / 1.4);
                break;
            case NW:
                this.position.setX(this.position.getX() + speed / 1.4);
                this.position.setY(this.position.getY() + speed / 1.4);
                break;
            case SE:
                this.position.setX(this.position.getX() - speed / 1.4);
                this.position.setY(this.position.getY() - speed / 1.4);
                break;
            case SW:
                this.position.setX(this.position.getX() + speed / 1.4);
                this.position.setY(this.position.getY() - speed / 1.4);
                break;
            default:

                break;
        }
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
