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

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameState extends State{
    private Player player;
    private List<Enemy> enemy1;
    private Shot shot;

    public GameState(Size windowSize, Input input) {
        super(windowSize, input);
        gameMap = new GameMap(new Size(20,20), spriteLibrary);
        player = new Player(new PlayerController(input) , spriteLibrary);
        enemy1 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            enemy1.add(new Enemy(new EnemyController(), spriteLibrary));
        }
        placeRandomEnimies(enemy1, this);
        gameObjects.add(player);
        gameObjects.addAll(enemy1);
        camera.focusOn(player);
    }

    private void placeRandomEnimies(List<Enemy> enemies, State state) {
        List<Point> possiblePosition = new ArrayList<>();
        for (int i = 0; i < state.getGameMap().getTiles().length; i++) {
            for (int j = 0; j < state.getGameMap().getTiles()[i].length; j++) {
                if(state.getMap("map1.txt")[i][j] != 1){
                    possiblePosition.add(new Point(i, j));
                }
            }
        }
        Random r = new Random();
        for (Enemy enemy: enemies) {
            Point randomPosition = possiblePosition.get(r.nextInt(possiblePosition.size()));
            enemy.setPosition( new Position(
                    randomPosition.x * Game.SPRITE_SIZE  - state.getCamera().getPosition().intX(),
                    randomPosition.y * Game.SPRITE_SIZE - state.getCamera().getPosition().intY())
            );
        }
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public List<Enemy> getEnemies(){
        return this.enemy1;
    }
}
