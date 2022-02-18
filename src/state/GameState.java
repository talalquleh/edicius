package state;


import controller.EnemyController;
import controller.PlayerController;
import entities.Enemy;
import entities.Player;
import game.Game;
import helpers.Position;
import helpers.Size;
import input.Input;
import worlds.GameMap;

import java.util.List;

public class GameState extends State{
    private Player player;

    public GameState(Size windowSize, Input input) {
        super(windowSize, input);
        gameMap = new GameMap(new Size(20,20), spriteLibrary);
        initializeEntities();
    }

    private void initializeEntities() {
        player = new Player(new PlayerController(input) , spriteLibrary);
        Enemy enemy1 = new Enemy(new EnemyController(), spriteLibrary);
        enemy1.setPosition(new Position(3 * Game.SPRITE_SIZE, 2 * Game.SPRITE_SIZE));
        gameObjects.addAll(List.of(player, enemy1));
        camera.focusOn(player);
    }

    @Override
    public Player getPlayer() {
        return player;
    }
}
