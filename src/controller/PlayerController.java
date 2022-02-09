package controller;

import java.awt.event.KeyEvent;
import input.Input;

/**
 * A class implements the Controller and has methods to determine the pressed key on the keyboard!
 */
public class PlayerController implements 	Controller {
	private Input input;
	
	public PlayerController(Input input) {
		this.input = input;
	}
	
	@Override
	public boolean isRequestingUp() {
		return input.isPressed(KeyEvent.VK_UP);
	}

	@Override
	public boolean isRequestingDown() {
		return input.isPressed(KeyEvent.VK_DOWN);
	}

	@Override
	public boolean isRequestingLeft() {
		return input.isPressed(KeyEvent.VK_LEFT);
	}

	@Override
	public boolean isRequestingRigth() {
		return input.isPressed(KeyEvent.VK_RIGHT);
	}

}
