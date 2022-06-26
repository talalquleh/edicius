package src.entities;

import src.controller.Controller;
import src.enemyAI.Brain;
import src.gfx.Animation;
import src.gfx.SpriteSheet;
import src.helpers.CollisionBox;
import src.helpers.Position;
import src.state.State;
import static src.display.Display.inRange;


import java.awt.*;

public class Enemy extends MovingEntity {

    private Brain brain;
    private boolean isShot;
    private String belongsTo;
    private Position target;
    private Position delta;
    private boolean timeToRemoveShot;
    private SpriteSheet changeSpriteLibrary;
    private long lastShootingTime = 0;

    private boolean isEnemyShot;
    private int healthPoints;
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
        this.delta = new Position(0,0);
        timeToRemoveShot  = false;
        changeSpriteLibrary = spriteLibrary;
        isEnemyShot = false;
        healthPoints = 60;
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

    public void setIsEnemyShot(boolean b){
        this.isEnemyShot = b;
    }

    public int getHealthPoints(){
        return this.healthPoints;
    }

    public void reduceHealthPoints(){
        this.healthPoints -= 1;
    }

    public boolean isShotFromEnemy(){
        return this.isEnemyShot;
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
    }

    public boolean isTimeToRemoveShot() {
        return timeToRemoveShot;
    }

    public void setTarget(Position position){
        this.target = position;
        this.delta =  new Position((position.getX() - this.position.getX()) * 0.03 , (position.getY() - this.position.getY()) * 0.03);
    }

    @Override
    public void update(State state){
        super.update(state);
        long milliseconds = System.currentTimeMillis();
        if (lastShootingTime + 1000 < milliseconds && !isShot() && inRange(state.getPlayer(), this)) {
            lastShootingTime = milliseconds;
            state.addEnemyShotsGameObjects(this);
        }
        if(isShot) {
            double x = this.position.getX() + delta.getX();
            double y = this.position.getY() + delta.getY();
            this.position = new Position( x, y);
            if (this.getCollisionBox().collidesWith(new CollisionBox
                (
                new Rectangle(target.intX(), target.intY(), 5, 5)
                )
            )){
                this.timeToRemoveShot = true;
            }
        }else{
            brain.update(state, this);
        }
    }
}
