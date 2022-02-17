package game;


import display.Display;
import entities.Player;
import settings.GameSettings;
import state.GameState;
import state.State;
import helpers.Size;
import input.Input;

public class Game {

    public static final int SPRITE_SIZE = 64;

    private Display display;
    private Input input;
    private State state;
    private GameSettings settings;

    /**
     * Initializing the keyboard inputs.
     * Displaying the game frame using the Display class with extends JFrame.
     * Initializing the game state so it can be used in rendering and updating the game!
     * @param width
     * @param height
     */
    public Game(int width, int height){
        input    = new Input();
        display  = new Display(width, height, input);
        state    = new GameState(new Size(width, height), input);
        settings = new GameSettings(true);
    }

    /**
     * Calling the update function on the State class.
     */
    public void update(){
        state.update();
    }

    /**
     * Calling the render function on the Display class and using the game state to draw the graphics properly.
     */
    public void render(){
        display.render(state, settings.isDebugMode());
    }

    public Player getPlayer(){
        return this.state.getPlayer();
    }

}