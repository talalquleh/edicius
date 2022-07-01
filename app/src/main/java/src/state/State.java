package src.state;

import src.controller.EnemyController;
import src.controller.PlayerController;
import src.entities.Enemy;
import src.game.Game;
import src.entities.Entity;
import src.entities.Player;
import src.gfx.SpriteSheet;
import src.helpers.Position;
import src.display.Display;
import src.helpers.Size;
import src.input.Input;
import src.worlds.GameMap;

import java.util.*;
import java.util.stream.Collectors;
import java.util.concurrent.CopyOnWriteArrayList;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import src.gfx.Camera;

public class State {

    protected GameMap gameMap;
    protected List<Entity> gameObjects ;
    protected SpriteSheet spriteLibrary;
    protected Input input;
    protected Camera camera;
    protected Player player;
    protected List<Enemy> enemies;
    public Position lastCameraPosition;
    public HashMap<Entity, Position> lastEntityPositions;
    protected int gameLevel=1;
    protected int enemiesCntToBeKilledPerLevel=0;

    public State(Size windowSize, Input input) {
        this.input = input;
        gameObjects = new CopyOnWriteArrayList<>();
        spriteLibrary = new SpriteSheet();
        gameMap = new GameMap(new Size(50, 50), spriteLibrary);
        camera = new Camera(windowSize);
        lastEntityPositions = new HashMap<>();
        // this.setEnemiesCntBasedOnLevel();
        enemies = new ArrayList<>();
        this.setEnemiesCntBasedOnLevel();

    }

    public Camera getCamera() {
        return camera;
    }

    /**
     * Updating all the game objects by calling their update methods.
     * Updating the camera position!
     */
    public void update(){
        sortObjectsByPosition();
        //List<Entity> copyGameObjects = new ArrayList<>(gameObjects);
        gameObjects.forEach(gameObject -> gameObject.update(this));
        camera.update(this);
    }

    /**
     * Sort the game object by their positions.
     * 
     */
    private void sortObjectsByPosition() {
        gameObjects.sort(Comparator.comparing(gameObject -> gameObject.getPosition().getY()));
    }

    public List<Entity> getGameObjects(){
        return gameObjects;
    }
    public GameMap getGameMap() {
        return this.gameMap;
    }

    public Player getPlayer() { return this.player ; }

    public Position getRandomPosition() {
        return gameMap.getRandomPosition();
    }
    public int getGameLevel(){
        return this.gameLevel;
    }

    public void increaseLevel(){
        this.gameLevel+=1;
    }
    public void setEnemiesCntBasedOnLevel(){
            this.enemiesCntToBeKilledPerLevel=this.gameLevel*2;
            // this.resetEnemiesPerLevel();
         
    }
    // public boolean upgradeLevel(){
    //     if(this.enemiesCntToBeKilledPerLevel==0){
    //        this.gameLevel+=1;
    //        this.setEnemiesCntBasedOnLevel();
    //         return true;
    //     }
    //     return false;
    // }

    //decrease enemies cnt and return if we can upgrade level
    public boolean decreaseEnemiesPerLevel(){
        this.enemiesCntToBeKilledPerLevel-=1;
        if(this.enemiesCntToBeKilledPerLevel==0){
           this.gameLevel+=1;
           this.setEnemiesCntBasedOnLevel();
            return true;
        }
        return false;
    }
   
    


    // public int getEnemiesCnt(){
    //     return this.enemiesCnt;
    // }

    /**
     * Get the entities that collides with each other.
     * @param entities
     * @return
     */
    public List<Entity> getCollidingEntities(Entity entities) {
        return this.gameObjects.stream().filter(other -> other.collidingWith(entities)).collect(Collectors.toList());
    }

    public int[][] getMap(String name){ return this.gameMap.getMaps(name);}

    public List<Enemy> getEnemies(){
        return null;
    }

    /**
     * Add a new entity to the game objects list.
     */
    public void addToGameObjects(){
    }

    public void resetEnemiesPerLevel(){
       
    }

    /**
     * Add a new shot.
     * @param en
     */
    public void addEnemyShotsGameObjects(Enemy en){
    }
// /**
    //  * Place enemies in random positions in the map.
    //  * 
    //  * @param enemies
    //  * @param state
    //  * @param enemyCount
    //  */
    public void placeRandomEnimies(List<Enemy> enemies, State state, int enemyCount) {        
        // System.out.println(this.enemiesCntToBeKilledPerLevel);
        for (int i = 0; i < enemyCount; i++) {
            Enemy enemy = new Enemy(new EnemyController(), spriteLibrary);
            enemies.add(enemy);
        }

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
}
