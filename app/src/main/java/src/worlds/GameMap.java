package src.worlds;

import src.game.Game;
import src.gfx.SpriteSheet;
import src.helpers.Position;
import src.helpers.Size;
import src.tiles.Tile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GameMap {
	private Tile[][] tiles;
	private Map<String, int[][]> maps;


	/**
	 * Initialize all the tails from the SpriteSheet provided!
	 * @param size
	 * @param spriteLibrary
	 */
	public GameMap(Size size, SpriteSheet spriteLibrary) {
		tiles = new Tile[(int) size.getWidth()][(int) size.getHeight()];
		initializeTiles(spriteLibrary);
		maps = new HashMap<>();
		loadMaps("/maps");
	}

	/**
	 * Initialize an empty matrix for the map tiles.
	 * 
	 * @param spriteSheet
	 */
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

	/**
	 * Load the map from a file.
	 * 
	 * @param path
	 */
	private void loadMaps(String path) {
		String [] mapsNames = getFilesFromFolder(path);
		maps = new HashMap<>();
		for (String m: mapsNames) {
			URL resource = SpriteSheet.class.getResource(path + "/" + m);
			File file ;
			if (resource == null) {
				throw new IllegalArgumentException("file not found!");
			}else {
				file = new File(resource.getFile());
				try {
					Scanner scanner = new Scanner(new FileReader(file));
					int[][] inds = new int[20][20];

					for (int i = 0; i < 20; i++) {
						for (int j = 0; j < 20; j++) {
							inds[i][j] = scanner.nextInt();
						}
					}
					maps.put(m, inds);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Get a file bath from inside a folder.
	 * 
	 * @param basePath
	 * @return a file bath from inside a folder.
	 */
	private String[] getFilesFromFolder(String basePath) {
		URL resource = SpriteSheet.class.getResource(basePath);
		File file;
		if (resource == null) {
			throw new IllegalArgumentException("file not found!");
		}else {
			file = new File(resource.getFile());
		}
		return file.list((current, name ) -> new File(current ,name).isFile());
	}

	public int[][] getMaps(String name){
		return this.maps.get(name);
	}
	 public int[][] getTilesValues(){
        return maps.get("map1.txt");
    }

}
