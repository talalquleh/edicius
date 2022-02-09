package display;

import java.awt.*;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import state.State;
import input.Input;

public class Display extends JFrame {

	private static final long serialVersionUID = 1L;


	private Canvas canvas;
	private Renderer renderer;

	/**
	 * Creating the game canvas and frame, also creating BufferStrategy to use it on the graphics rendering!
	 * @param width
	 * @param height
	 * @param input
	 */
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

	/**
	 * Initializing and implementing the graphics that is going to be used on the game.
	 * Using the render method from the Renderer class to render those grahics!
	 * @param state
	 */
	public void render(State state){
		BufferStrategy bufferStartegy = canvas.getBufferStrategy();
		Graphics graphics = bufferStartegy.getDrawGraphics();

		graphics.setColor(Color.BLACK);

		graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

		renderer.render(state, graphics);

		graphics.dispose();
		bufferStartegy.show(); // here the displaying of the whole graphics is happening

	}

}
