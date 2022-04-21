package src.display;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import src.game.GameLoop;
import src.helpers.Position;
import src.state.GameState;
import src.state.State;
import src.input.Input;

public class Display extends JFrame {

	private static final long serialVersionUID = 1L;

	public static Position mousePosition = new Position(0, 0);
	public static Boolean shooting = false;


	private Canvas canvas;
	private Renderer renderer;
	private DebugRenderer debugRenderer;

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
		debugRenderer = new DebugRenderer();
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, height));
		canvas.setFocusable(false);
		add(canvas);

		try {
			Image customImage = ImageIO.read(new File("./cursor.png"));
			Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(customImage, new Point(0, 0), "customCursor");
			this.setCursor(customCursor);
		} catch (IOException e) {
			e.printStackTrace();
		}

		canvas.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				mousePosition = new Position(e.getX(), e.getY());
				shooting = true;
			}

			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
		});

		addKeyListener(input);
		pack();

		canvas.createBufferStrategy(2);

		setLocationRelativeTo(null);
		setVisible(true);
	}

	/**
	 * Initializing and implementing the graphics that is going to be used on the game.
	 * Using the render method from the Renderer class to render those grahics!
	 * @param state
	 */
	public void render(State state, boolean debugMode){
		BufferStrategy bufferStartegy = canvas.getBufferStrategy();
		Graphics graphics = bufferStartegy.getDrawGraphics();


		if(shooting){
			mousePosition = new Position(mousePosition.getX() + state.lastCameraPosition.getX() , mousePosition.getY() + state.lastCameraPosition.getY());
			state.addToGameObjects();
			shooting = false;
		}

		renderer.render(state, graphics);

		if(debugMode){
			debugRenderer.render(state, graphics);
		}

		graphics.dispose();
		bufferStartegy.show(); // here the displaying of the whole graphics is happening

	}

}