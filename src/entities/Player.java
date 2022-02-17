package entities;


import controller.Controller;
import gfx.SpriteSheet;
import helpers.CollisionBox;

import java.util.List;

public class Player extends MovingEntity {
	/**
	 * Takes a Controller and a SpriteSheet that are going to identify all the components of the player!
	 * @param controller
	 * @param spriteLibrary
	 */
	public Player(Controller controller, SpriteSheet spriteLibrary) {
		super(controller, spriteLibrary);
	}

	@Override
	protected void handleCollision(Entity other) {
		if(other instanceof Enemies){
			((Enemies) other).motion.stop();
		}

		for (CollisionBox box: getMapCollisionBoxes() ) {
			if (box.collidesWith(this.getCollisionBox())) this.motion.stop();
		}
	}
}
