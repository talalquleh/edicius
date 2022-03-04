package display;

//import java.awt.Graphics;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import entities.Entity;
import entities.Shot;
import game.Game;
import helpers.Position;
import state.State;
import tiles.Tile;
import gfx.Camera;

import javax.imageio.ImageIO;

public class Renderer {

	/**
	 * Getting the game objects from and drawing them into our frame
	 * @param state
	 * @param graphics
	 */
	public void render(State state, Graphics graphics) {
		renderMap(state , graphics);
		Camera camera = state.getCamera();
		for (Entity gameObject: state.getGameObjects()) {

			if(gameObject instanceof Shot){
				Shot shot = ((Shot) gameObject);
				graphics.drawImage( shot.getShotImage(),
						gameObject.getPosition().intX() - camera.getPosition().intX() - (int) gameObject.getSize().getWidth() / 2,
						gameObject.getPosition().intY() - camera.getPosition().intY() - (int) gameObject.getSize().getHeight() / 2,
						15,
						15,
						null
				);
				shot.shoot(new Position(Display.mousePosition.getX(), Display.mousePosition.getY()));

			}else{
				graphics.drawImage(
						gameObject.getSprite(),
						gameObject.getPosition().intX() - camera.getPosition().intX() - (int) gameObject.getSize().getWidth() / 2,
						gameObject.getPosition().intY() - camera.getPosition().intY() - (int)gameObject.getSize().getHeight() / 2,
						null
				);
			}
		}
	}

	/**
	 * Rendering the game map using the tiles stored in the game state and positioning the camera to focus on the player!
	 * @param state
	 * @param graphics
	 */
	private void renderMap(State state, Graphics graphics) {
		Tile[][] tiles = state.getGameMap().getTiles();
		Camera camera = state.getCamera();
		for(int x = 0; x < tiles.length; x++){
			for(int y = 0 ; y < tiles[0].length; y++){
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
