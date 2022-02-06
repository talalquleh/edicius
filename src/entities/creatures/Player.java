package entities.creatures;


import controller.Controller;
import gfx.SpriteSheet;
import entities.MovingEntity;

public class Player extends MovingEntity {
	public Player(Controller controller, SpriteSheet spriteLibrary) {
		super(controller, spriteLibrary);
	}
}
