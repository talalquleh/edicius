package entities;

import controller.Controller;
import enemyAI.Brain;
import gfx.Animation;
import gfx.SpriteSheet;
import helpers.CollisionBox;
import state.State;

public class Enemy extends MovingEntity {

    private Brain brain;
    /**
     * Initilize all the components that are going to be used to update an entity!
     *
     * @param controller
     * @param spriteLibrary
     */
    public Enemy(Controller controller, SpriteSheet spriteLibrary) {
        super(controller, spriteLibrary);
        animation = new Animation(spriteLibrary.getUnit("matt"));
        brain = new Brain();
    }

    @Override
    protected void handleCollision(Entity other) {
        for (CollisionBox box: getMapCollisionBoxes() ) {
            if (box.collidesWith(this.getCollisionBox())) {
                handleWallCollision(this.motion.getSpeed());
            }
        }
    }


    @Override
    public void update(State state){
        super.update(state);
        brain.update(state, this);
    }
}