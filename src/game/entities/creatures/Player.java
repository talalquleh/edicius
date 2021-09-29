package game.entities.creatures;



public class Player extends Creature {

	private Controller controller;

	public Player(Controller controller) {
		super();
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
		position.setLocation(position.getX() + deltaX, position.getY() + deltaY);
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
