package worlds;

import java.awt.Graphics;

public class World {
	private int width,height;
	private int[][]tiles;
	
	
	//we will load worlds form a file
	//later we will edit random worlds generation
	public World(String path)
	{
		loadWorld(path);
	}
	public void update() {
		
	}
	public void render(Graphics g) {
		
	}
	private void loadWorld(String path)
	{

	}
}
