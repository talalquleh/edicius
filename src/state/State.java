package state;

import entities.Entity;
import game.Time;
import gfx.SpriteSheet;
import helpers.Position;
import helpers.Size;
import input.Input;
import worlds.GameMap;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import gfx.Camera;

public class State {

    protected GameMap gameMap;
    protected List<Entity> gameObjects ;
    protected SpriteSheet spriteLibrary;
    protected Input input;
    protected Camera camera;
    protected Time time;

    public State(Size windowSize, Input input) {
        this.input = input;
        gameObjects = new ArrayList<>();
        spriteLibrary = new SpriteSheet();
        gameMap = new GameMap(new Size(50, 50), spriteLibrary);
        camera = new Camera(windowSize);
        time = new Time();
    }

    public Time getTime() {
        return time;
    }

    public Camera getCamera() {
        return camera;
    }

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

    public Position getRandomPosition() {
        return gameMap.getRandomPosition();
    }
}
