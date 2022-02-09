package worlds;

import game.Game;
import gfx.SpriteSheet;
import helpers.Position;
import helpers.Size;
import tiles.Tile;

import java.util.Arrays;

public class GameMap {
	private Tile[][] tiles;

	/**
	 * Initialize all the tails from the SpriteSheet provided!
	 * @param size
	 * @param spriteLibrary
	 */
	public GameMap(Size size, SpriteSheet spriteLibrary) {
		tiles = new Tile[(int) size.getWidth()][(int) size.getHeight()];
		initializeTiles(spriteLibrary);
	}

	private void initializeTiles(SpriteSheet spriteSheet) {
		for (Tile[] row: tiles){
			Arrays.fill(row, new Tile(spriteSheet));
		}
	}

	public Tile[][] getTiles() {
		return tiles;
	}

	public double getHeight() {
		return tiles[0].length * Game.SPRITE_SIZE;
	}

	public double getWidth() {
		return tiles.length * Game.SPRITE_SIZE;
	}

	public Position getRandomPosition() {
		double x = Math.random() * tiles.length * Game.SPRITE_SIZE;
		double y = Math.random() * tiles[0].length * Game.SPRITE_SIZE;
		return new Position(x, y);
	}
}
