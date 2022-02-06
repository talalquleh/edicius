package display;

import java.awt.Graphics;

import game.Game;
import state.State;
import tiles.Tile;
import gfx.Camera;

public class Renderer {

	public void render(State state, Graphics graphics) {
		renderMap(state , graphics);
		Camera camera = state.getCamera();
		state.getGameObjects().forEach(gameObject -> graphics.drawImage(
				gameObject.getSprite(),
				(int) gameObject.getPosition().intX() - camera.getPosition().intX() -(int) gameObject.getSize().getWidth() / 2,
				(int) gameObject.getPosition().intY() - camera.getPosition().intY() - (int)gameObject.getSize().getHeight() / 2,
				null));
	}

	private void renderMap(State state, Graphics graphics) {
		Tile[][] tiles = state.getGameMap().getTiles();
		Camera camera = state.getCamera();
		for(int x = 0; x < tiles.length; x++){
			for(int y = 0 ; y < tiles[0].length; y++){
				graphics.drawImage(
						tiles[x][y].getSprite(),
						x * Game.SPRITE_SIZE - camera.getPosition().intX(),
						y * Game.SPRITE_SIZE - camera.getPosition().intY(),
						null
				);
			}
		}
	}
}
