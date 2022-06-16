package src.entities;


import src.controller.Controller;
import src.gfx.SpriteSheet;
import src.helpers.CollisionBox;

public class Player extends MovingEntity {

	private int healthPoints = 1000;

	/**
	 * Takes a Controller and a SpriteSheet that are going to identify all the components of the player!
	 *
	 * @param controller
	 * @param spriteLibrary
	 */
	public Player(Controller controller, SpriteSheet spriteLibrary) {
		super(controller, spriteLibrary);
		motion.setMotion(4.0);
	}
private  int health=100; private int killedCnt = 0;
	@Override
	protected void handleCollision(Entity other) {
		if (other instanceof Enemy) {
			((Enemy) other).motion.stop();
		}
		for (CollisionBox box : getMapCollisionBoxes()) {
			if (box.collidesWith(this.getCollisionBox())) {
				handleWallCollision(this.motion.getSpeed());
			}
		}
	}
	public void setHealth(int health){
		this.health=health;
	}
	public  int getHealth(){
		return this.health;
	}
	public  boolean isAlive(){
		return this.health>0;
	}
       public void setKilledCnt(int killedCnt) {
        this.killedCnt = killedCnt;
    }

    public int getKilledCnt() {
        return this.killedCnt;
    }
	public int getHealthPoints(){
		return healthPoints;
	}

	public void reduceHealthPoints(){
		this.healthPoints -= 1;
	}

}
