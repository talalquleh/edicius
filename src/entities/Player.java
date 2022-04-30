package entities;


import controller.Controller;
import gfx.SpriteSheet;
import helpers.CollisionBox;

public class Player extends MovingEntity {
	/**
	 * Takes a Controller and a SpriteSheet that are going to identify all the components of the player!
	 * @param controller
	 * @param spriteLibrary
	 */
	public Player(Controller controller, SpriteSheet spriteLibrary) {
		super(controller, spriteLibrary);motion.setMotion(4.0);
	}

	@Override
	protected void handleCollision(Entity other) {
		if(other instanceof Enemy){
			((Enemy) other).motion.stop();
		}
		for (CollisionBox box: getMapCollisionBoxes() ) {
			if (box.collidesWith(this.getCollisionBox())) {
				handleWallCollision(this.motion.getSpeed());
			}
		}
	}
}