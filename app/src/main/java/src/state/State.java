package src.state;

import src.entities.Enemy;
import src.entities.Entity;
import src.entities.Player;
import src.gfx.SpriteSheet;
import src.helpers.Position;
import src.helpers.Size;
import src.input.Input;
import src.worlds.GameMap;

import java.util.*;
import java.util.stream.Collectors;
import java.util.concurrent.CopyOnWriteArrayList;

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
    protected int enemiesCnt=0;

    public State(Size windowSize, Input input) {
        this.input = input;
        gameObjects = new CopyOnWriteArrayList<>();
        spriteLibrary = new SpriteSheet();
        gameMap = new GameMap(new Size(50, 50), spriteLibrary);
        camera = new Camera(windowSize);
        lastEntityPositions = new HashMap<>();
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
            this.enemiesCnt=this.gameLevel*2;
    }
    public int getEnemiesCnt(){
        return this.enemiesCnt;
    }

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

    /**
     * Add a new shot.
     * @param en
     */
    public void addEnemyShotsGameObjects(Enemy en){
    }

}
