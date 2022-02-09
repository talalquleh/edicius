package entities;


import controller.Controller;
import gfx.SpriteSheet;

public class Player extends MovingEntity {
	/**
	 * Takes a Controller and a SpriteSheet that are going to identify all the components of the player!
	 * @param controller
	 * @param spriteLibrary
	 */
	public Player(Controller controller, SpriteSheet spriteLibrary) {
		super(controller, spriteLibrary);
	}
}
