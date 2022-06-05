package src.state;


import src.controller.EnemyController;
import src.controller.PlayerController;
import src.display.Display;
import src.entities.Enemy;
import src.entities.Player;
import src.game.Game;
import src.helpers.Position;
import src.helpers.Size;
import src.input.Input;
import src.worlds.GameMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameState extends State{

    public GameState(Size windowSize, Input input) {
        super(windowSize, input);
        gameMap = new GameMap(new Size(20,20), spriteLibrary);
        player = new Player(new PlayerController(input) , spriteLibrary);
        enemies = new ArrayList<>();
        final int enemyCount = 10;
        for (int i = 0; i < enemyCount; i++) {
            Enemy enemy = new Enemy(new EnemyController(), spriteLibrary);
            enemies.add(enemy);
        }

        placeRandomEnimies(enemies, this);
        gameObjects.add(player);
        gameObjects.addAll(enemies);
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
                    randomPosition.x * Game.SPRITE_SIZE + Game.SPRITE_SIZE/2,
                    randomPosition.y * Game.SPRITE_SIZE + Game.SPRITE_SIZE/2)
            );
        }
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public List<Enemy> getEnemies(){
        return this.enemies;
    }

    @Override
    public void addToGameObjects(){
        Enemy en = new Enemy(new EnemyController(), spriteLibrary);
        en.setPosition(player.getPosition());
        en.setTarget(Display.mousePosition);
        en.isShot(spriteLibrary);

        gameObjects.add(en);

    }
}
