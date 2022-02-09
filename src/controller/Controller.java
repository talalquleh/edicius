package controller;

/**
 * An interface to specify the functions needed for the controller to determine what key was pressed!
 */
public interface Controller {
	boolean  isRequestingUp();
	boolean  isRequestingDown();
	boolean  isRequestingLeft();
	boolean  isRequestingRigth();
}
