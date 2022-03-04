package state;


import controller.EnemyController;
import controller.PlayerController;
import entities.Enemy;
import entities.Player;
import entities.Shot;
import game.Game;
import helpers.Position;
import helpers.Size;
import input.Input;
import worlds.GameMap;

import java.util.List;

public class GameState extends State{
    private Player player;
    private Enemy enemy1;
    private Shot shot;

    public GameState(Size windowSize, Input input) {
        super(windowSize, input);
        gameMap = new GameMap(new Size(20,20), spriteLibrary);
        player = new Player(new PlayerController(input) , spriteLibrary);
        enemy1 = new Enemy(new EnemyController(), spriteLibrary);
        enemy1.setPosition(new Position(3 * Game.SPRITE_SIZE, 2 * Game.SPRITE_SIZE));
        shot = new Shot(new EnemyController(), spriteLibrary);
        shot.setPosition(player.getPosition());
        gameObjects.addAll(List.of(player, enemy1, shot));
        camera.focusOn(player);
    }


    @Override
    public Player getPlayer() {
        return player;
    }
}
