package src.game;


import src.display.Display;
import src.entities.Player;
import src.settings.GameSettings;
import src.state.GameState;
import src.state.State;
import src.helpers.Size;
import src.input.Input;

public class Game {

    public static final int SPRITE_SIZE = 64;
    public static int  COUNT_OF_ENEMIES = 10;
    public static String game_state = "NONE";

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

    public static void log(String str) {
        System.out.println(str);
    }

    /**
     * Calling the update function on the State class.
     */
    public void update(){
        state.update();
    }
    public Display getDisplay(){
        return this.display;
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