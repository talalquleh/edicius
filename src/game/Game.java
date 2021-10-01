package game;
import java.util.ArrayList;
import java.util.List;

import controller.PlayerController;
import display.Display;
import entities.creatures.Creature;
import entities.creatures.Player;
import input.Input;

public class Game {
    private Display display;
    private List<Creature> creatures ;
    private Input input;


    public Game(int width, int height){
    	input = new Input();
        display = new Display(width, height, input);
        creatures = new ArrayList<>();
        creatures.add(new Player(new PlayerController(input)));
    }

    public void update(){
        creatures.forEach(gameObject -> gameObject.update());
    }
    public void render(){
        display.render(this);
    }

    public List<Creature> getGameObjects(){
        return creatures;
    }

}
