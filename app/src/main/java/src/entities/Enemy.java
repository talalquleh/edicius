package src.entities;

import src.controller.Controller;
import src.enemyAI.Brain;
import src.gfx.Animation;
import src.gfx.SpriteSheet;
import src.helpers.CollisionBox;
import src.helpers.Position;
import src.state.State;

import java.awt.*;

public class Enemy extends MovingEntity {

    private Brain brain;
    private boolean isShot;
    private String belongsTo;
    private Position target;
    private boolean timeToRemoveShot;
    private SpriteSheet changeSpriteLibrary;
    /**
     * Initilize all the components that are going to be used to update an entity!
     *
     * @param controller
     * @param spriteLibrary
     */
    public Enemy(Controller controller, SpriteSheet spriteLibrary) {
        super(controller, spriteLibrary);
        animation = new Animation(spriteLibrary.getUnit("matt"));
        //brain = new Brain(getMapCollisionBoxes(), this.getCollisionBox());
        brain = new Brain();
        this.isShot = false;
        this.target = new Position(0,0);
        timeToRemoveShot  = false;
        changeSpriteLibrary = spriteLibrary;
    }

    public void isShot(SpriteSheet spriteLibrary){
        this.animation = new Animation(spriteLibrary.getUnit("shot"));
        this.isShot = true;
    }

    public void killPlayer(){
        this.animation = new Animation(this.changeSpriteLibrary.getUnit("shot"));
    }

    public boolean isShot() {
        return isShot;
    }

    public String getBelongsTo() {
        return belongsTo;
    }

    public void setBelongsTo(String belongsTo) {
        this.belongsTo = belongsTo;
    }

    @Override
    protected void handleCollision(Entity other) {
        for (CollisionBox box: getMapCollisionBoxes() ) {
            if (box.collidesWith(this.getCollisionBox())) {
                if(this.isShot){
                    timeToRemoveShot = true;
                }else{
                    handleWallCollision(this.motion.getSpeed());
                }
            }
        }
        // if(other instanceof Enemy && this.isShot){
        //     if(((Enemy) other).getCollisionBox().collidesWith(this.getCollisionBox())){
        //         timeToRemoveShot = true;
        //     }
        // }

    }

    public boolean isTimeToRemoveShot() {
        return timeToRemoveShot;
    }

    public void setTarget(Position position){
        this.target = position;
    }

    @Override
    public void update(State state){
        super.update(state);
        if(isShot) {
            double deltaX = target.getX() - this.position.getX() ;
            double deltaY = target.getY() - this.position.getY();
            double x = this.position.getX() + deltaX * 0.05;
            double y = this.position.getY() + deltaY * 0.05;
            this.position = new Position( x, y);
            if (this.getCollisionBox().collidesWith(new CollisionBox
                (
                new Rectangle(target.intX(), target.intY(), 15, 15)
                )
            )){
                this.timeToRemoveShot = true;
            }
        }else{
            brain.update(state, this);
        }
    }
}
