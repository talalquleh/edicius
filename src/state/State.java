package state;

import entities.Enemy;
import entities.Entity;
import entities.Player;
import entities.Shot;
import gfx.SpriteSheet;
import helpers.Position;
import helpers.Size;
import input.Input;
import worlds.GameMap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import gfx.Camera;

public class State {

    protected GameMap gameMap;
    protected List<Entity> gameObjects ;
    protected SpriteSheet spriteLibrary;
    protected Input input;
    protected Camera camera;
    protected List<Shot> shots;
    public Position lastCameraPosition;
    public HashMap<Entity, Position> lastEntityPositions;

    public State(Size windowSize, Input input) {
        this.input = input;
        gameObjects = new ArrayList<>();
        spriteLibrary = new SpriteSheet();
        gameMap = new GameMap(new Size(50, 50), spriteLibrary);
        camera = new Camera(windowSize);
        shots = new ArrayList<>();
        lastEntityPositions = new HashMap<>();
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
        gameObjects.forEach(gameObject -> gameObject.update(this));
        camera.update(this);
    }

    private void sortObjectsByPosition() {
        gameObjects.sort(Comparator.comparing(gameObject -> gameObject.getPosition().getY()));
    }

    public List<Entity> getGameObjects(){
        return gameObjects;
    }
    public GameMap getGameMap() {
        return this.gameMap;
    }

    public Player getPlayer() { return null; }

    public Position getRandomPosition() {
        return gameMap.getRandomPosition();
    }

    public List<Entity> getCollidingEntities(Entity entities) {
        return this.gameObjects.stream().filter(other -> other.collidingWith(entities)).collect(Collectors.toList());
    }

    public int[][] getMap(String name){ return this.gameMap.getMaps(name);}

    public void addShot(Shot shot){
        this.shots.add(shot);
    }

    public List<Shot> getShots() {
        return shots;
    }

    public List<Enemy> getEnemies(){
        return null;
    }
}
