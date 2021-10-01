package entities.creatures;


import controller.Controller;
import helper_classes.Position;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Creature {

	private Controller controller;

	public Player(Controller controller) {
		super(controller);
		this.controller = controller;
	}

	@Override
	public void update() {
		int deltaX = 0, deltaY = 0;

		if (controller.isRequestingUp()) {
			deltaY--;
		}
		if (controller.isRequestingDown()) {
			deltaY++;
		}
		if (controller.isRequestingLeft()) {
			deltaX--;
		}
		if (controller.isRequestingRigth()) {
			deltaX++;
		}
		position= new Position(position.getX() + deltaX, position.getY() + deltaY);
	}

	@Override
	public Image getSprite() {
		BufferedImage image = new BufferedImage((int) size.getWidth(), (int) size.getWidth(),
				BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = image.createGraphics();
		graphics.setColor(Color.BLUE);
		graphics.fillRect(0, 0, (int) size.getWidth(), (int) size.getHeight());
		graphics.dispose();

		return image;
	}

}
