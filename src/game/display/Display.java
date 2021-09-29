package game.display;

import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import game.Game;
import input.Input;

public class Display extends JFrame {

	private static final long serialVersionUID = 1L;
	
	
	private Canvas canvas;
	private Renderer renderer;
	
	public Display(int width, int height , Input input) {
		setTitle("Game");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	
		renderer = new Renderer();
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setFocusable(false);
		add(canvas);
		addKeyListener(input);
		pack();

		canvas.createBufferStrategy(3);

		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void render(Game game){
		BufferStrategy bufferStartegy = canvas.getBufferStrategy();
		Graphics graphics = bufferStartegy.getDrawGraphics();

		graphics.setColor(Color.BLACK);

		graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

		renderer.render(game, graphics);

		graphics.dispose();
		bufferStartegy.show();
		
	}

}
