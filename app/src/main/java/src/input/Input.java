package src.input;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

public class Input implements KeyListener, MouseListener {
	
	private boolean[] pressed ;
	private Map<Boolean, Point> mouseClicked;
	public Input() {
		pressed = new boolean[255];
		mouseClicked = new HashMap<>(1);
	}
	
	public boolean isPressed(int keyCode) {
		return pressed[keyCode];
	}
	public Map<Boolean, Point> isClicked(){
		return mouseClicked;
	}


	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		pressed[e.getKeyCode()] = true;	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		pressed[e.getKeyCode()] = false ;
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		mouseClicked.put(true, new Point(0, 0));
		mouseClicked.get(true);
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
}
