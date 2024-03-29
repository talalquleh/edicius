package src.display;

import java.awt.*;
import java.util.ArrayList;
import  src.state.GameMenu;

import src.entities.Enemy;
import src.entities.Entity;
import src.game.Game;
import src.helpers.Position;
import src.helpers.Size;
import src.state.State;
import src.tiles.Tile;
import src.gfx.Camera;
import src.helpers.buttons.MusicButton;
import src.helpers.buttons.PauseButton;


public class Renderer {

	/**
	 * Get a rectangle that determine the pixels position of an entity.
	 * 
	 * @param pos
	 * @param s
	 * @return position pox.
	 */
	public  static Rectangle entityToPixel(Position pos, Size s)
	{
		return new Rectangle(pos.intX() - (int)s.getWidth() / 2,
				pos.intY() - (int)s.getHeight() / 2,
				(int)s.getWidth(), (int)s.getHeight());
	}

	/**
	 * Get a rectangle that determine the tails position of an entity.
	 * 
	 * @param r
	 * @return rectangle tail.
	 */
	public static Rectangle entityToTile(Rectangle r)
	{
		return new Rectangle(r.x / Game.SPRITE_SIZE, r.y / Game.SPRITE_SIZE,
				(r.x + r.width)  / Game.SPRITE_SIZE - r.x / Game.SPRITE_SIZE + 1,
				(r.y + r.height) / Game.SPRITE_SIZE - r.y / Game.SPRITE_SIZE + 1);
	}
	/**
	 * Getting the game objects from and drawing them into our frame
	 * @param state
	 * @param graphics
	 */
	public void render(State state, Graphics graphics) {
		Tile[][] tiles = state.getGameMap().getTiles();
		Camera camera = state.getCamera();
		boolean renderedFullMap = false;
		if (!camera.getPosition().equals(state.lastCameraPosition)) {
			state.lastCameraPosition = new Position(camera.getPosition().getX(), camera.getPosition().getY());
			renderMap(state, graphics, 0, 0, tiles.length, tiles[0].length);
			renderedFullMap = true;
		}

		java.util.List<Entity> changedEntities = new ArrayList<>();
		java.util.List<Rectangle> changedRects = new ArrayList<>();
		for (Entity gameObject : state.getGameObjects()) {
			Rectangle initRect = entityToTile(entityToPixel(gameObject.getPosition(), gameObject.getSize()));
			final Rectangle rect;

			if (!state.lastEntityPositions.containsKey(gameObject)) {
				rect = initRect;
			}
			else{
				rect = initRect.union(entityToTile(entityToPixel(state.lastEntityPositions.get(gameObject), gameObject.getSize())));
			}
			if (state.lastEntityPositions.containsKey(gameObject) && state.lastEntityPositions.get(gameObject) == gameObject.getPosition()) {
				if (changedRects.stream().noneMatch(rect::intersects)) continue;
			}
			if (!renderedFullMap){
				renderMap(state, graphics, rect.x, rect.y, rect.x + rect.width, rect.y + rect.height);
			}
			state.lastEntityPositions.put(gameObject, new Position(gameObject.getPosition().getX(), gameObject.getPosition().getY()));

			changedEntities.add(gameObject);
			changedRects.add(rect);
		}
		for (Entity gameObject: changedEntities) {
			graphics.drawImage(
					gameObject.getSprite(),
					gameObject.getPosition().intX() - camera.getPosition().intX() - (int) gameObject.getSize().getWidth() / 2,
					gameObject.getPosition().intY() - camera.getPosition().intY() - (int) gameObject.getSize().getHeight() / 2,
					null
			);
		}
		for (Entity nextElem : changedEntities) {
			if (nextElem instanceof Enemy) {
				Enemy shot = (Enemy) nextElem;
				if (shot.isShot()){
					if (shot.isTimeToRemoveShot()) {
						state.getGameObjects().remove(nextElem);
					}
	
					if (shot.isShotFromEnemy() && shot.collidingWith(state.getPlayer())){
						if(state.getPlayer().getHealthPoints() == 0)
							state.getGameObjects().remove(state.getPlayer()); // game over
						else 
							state.getPlayer().reduceHealthPoints();
					}
	
					 for (Entity entity : changedEntities) {
					 	if (entity instanceof Enemy) {
					 		Enemy enemy = (Enemy) entity; 
					 		if(!enemy.isShot() && !shot.isShotFromEnemy() && shot.collidingWith(enemy)){
					 			if(enemy.getHealthPoints() == 0){
					 				state.getGameObjects().remove(enemy);
									// Game.COUNT_OF_ENEMIES -= 1;
									state.decreaseEnemiesPerLevel();
									state.getPlayer().increaseKillCnt();
								}
					 			else 
					 				enemy.reduceHealthPoints();
					 		}	
					 	}
					 }
				}
			}
		}
		// if(state.getPlayer().getKillCnt == 10){
		// 	Game.game_state = "WIN";
		// }
		if(state.getPlayer().getHealthPoints() == 0
		||state.getPlayer().getKillCnt()==10	){
			State.gameOver=true;
			State.totalKills=state.getPlayer().getKillCnt();
			return;
			// new GameOver(state.getPlayer().getKillCnt());
		}
		// if(Game.game_state != "NONE"){
		// 	new GameOver(Game.game_state);
		// }
		//in case player dead

        //progress bars and button
        //music button
        graphics.setColor(new Color(40, 41, 60));
        graphics.fillRect(700 - 120, 5, 300, 40); //for health bar
        graphics.fillRect(MusicButton.instance.x, MusicButton.instance.y, MusicButton.instance.width, MusicButton.instance.height);
        graphics.setColor(Color.white);
        //pause button
        graphics.setColor(new Color(40, 41, 60));
        graphics.fillRect(PauseButton.instance.x, PauseButton.instance.y, PauseButton.instance.width, PauseButton.instance.height);
        graphics.setColor(Color.white);
        //health bar
        // graphics.drawString("Heath: " + state.getPlayer().getHealth() + " HP", 700 - 120, 25); 
		 graphics.drawString("Health: " + state.getPlayer().getHealthPoints() + " HP", 700 - 120, 25);
        graphics.drawString(MusicButton.instance.btn_text, MusicButton.instance.x + 10, MusicButton.instance.y + 20); //for music
        graphics.drawString(PauseButton.instance.btn_text, PauseButton.instance.x + 10, PauseButton.instance.y + 20); //for pausing
		graphics.setFont(new Font("Ink Free", Font.BOLD, 20));
		FontMetrics metrics2 = graphics.getFontMetrics(graphics.getFont());
		graphics.drawString("killed: " + state.getPlayer().getKillCnt(), (700 - metrics2.stringWidth("killed: " + state.getPlayer().getKillCnt())), 700 - graphics.getFont().getSize());//draw killed enemies
		graphics.setFont(new Font("Ink Free", Font.BOLD, 10));
		graphics.drawString("LEVEL: " + state.getGameLevel(), (700 - metrics2.stringWidth("level: " + state.getGameLevel())), 700 - graphics.getFont().getSize());//level of player
        //when pausing draw string
        if (PauseButton.instance.isPaused) {
            graphics.setColor(Color.red);
            graphics.setFont(new Font("Monospaced", Font.BOLD, 75));
            FontMetrics metrics3 = graphics.getFontMetrics(graphics.getFont());
            graphics.drawString("Paused", (700 - metrics3.stringWidth("Paused")) / 2, 700 / 2);
            graphics.setFont(new Font("Ink Free", Font.BOLD, 20));

        } //progress bars and button

	}

	/**
	 * Rendering the game map using the tiles stored in the game state and positioning the camera to focus on the player!
	 * @param state
	 * @param graphics
	 */
	private void renderMap(State state, Graphics graphics, int startX, int startY, int endX, int endY) {
		Tile[][] tiles = state.getGameMap().getTiles();
		Camera camera = state.getCamera();
		startX = Math.max(0, startX);
		startY = Math.max(0, startY);
		endX = Math.min(tiles.length, endX);
		endY = Math.min(tiles[0].length, endY);
		for(int x = startX; x < endX; x++){
			for(int y = startY; y < endY; y++){
				graphics.drawImage(
						(state.getMap("map1.txt")[x][y] == 1) ? tiles[x][y].getWall() : tiles[x][y].getFloor(),
						x * Game.SPRITE_SIZE - camera.getPosition().intX(),
						y * Game.SPRITE_SIZE - camera.getPosition().intY(),
						null
				);
			}
		}
	}
	
}
