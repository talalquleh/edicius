package src.display;

import java.awt.*;
import java.util.ArrayList;

import src.entities.Enemy;
import src.entities.Entity;
import src.game.Game;
import src.helpers.Position;
import src.helpers.Size;
import src.state.State;
import src.tiles.Tile;
import src.gfx.Camera;


public class Renderer {

	public Rectangle entityToPixel(Position pos, Size s)
	{
		return new Rectangle(pos.intX() - (int)s.getWidth() / 2,
				pos.intY() - (int)s.getHeight() / 2,
				(int)s.getWidth(), (int)s.getHeight());
	}
	public Rectangle entityToTile(Rectangle r)
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
		for (int i = 0; i < state.getGameObjects().size(); i++) {
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
				Enemy en = (Enemy) nextElem;
				if (en.isTimeToRemoveShot()) {
					state.getGameObjects().remove(nextElem);
				}
			}
		}

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
