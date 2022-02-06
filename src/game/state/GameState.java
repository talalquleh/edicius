package game.state;


import controller.PlayerController;
import entities.creatures.Player;
import helper_classes.Size;
import input.Input;
import worlds.GameMap;

import java.util.List;

public class GameState extends State{

    public GameState(Size windowSize, Input input) {
        super(windowSize, input);
        gameMap = new GameMap(new Size(20,20), spriteLibrary);
        initializeCharacters();
    }

    private void initializeCharacters() {
        Player player = new Player(new PlayerController(input) , spriteLibrary);
        gameObjects.addAll(List.of(player));
        camera.focusOn(player);
    }
}
