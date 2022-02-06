package game;


import display.Display;
import game.state.GameState;
import game.state.State;
import helper_classes.Size;
import input.Input;

public class Game {

    public static final int SPRITE_SIZE = 64;

    private Display display;
    private Input input;
    private State state;

    public Game(int width, int height){
        input   = new Input();
        display = new Display(width, height, input);
        state   = new GameState(new Size(width, height), input);
    }

    public void update(){
        state.update();
    }

    public void render(){
        display.render(state);
    }

}