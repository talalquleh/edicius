package game.main;
import java.util.ArrayList;
import java.util.List;

import controller.PlayerController;
import display.Display;
import entity.GameObject;
import entity.Player;
import input.Input;

public class Game {
    private Display display;
    private List<GameObject> gameObjects ;
    private Input input;


    public Game(int width, int height){
    	input = new Input();
        display = new Display(width, height, input);
        gameObjects = new ArrayList<>();
        gameObjects.add(new Player(new PlayerController(input)));
    }

    public void update(){
        gameObjects.forEach(gameObject -> gameObject.update());
    }
    public void render(){
        display.render(this);
    }

    public List<GameObject> getGameObjects(){
        return gameObjects;
    }

}
