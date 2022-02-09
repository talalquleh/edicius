package entities.creatures;

import controller.Controller;
import entities.Entity;
import state.State;
import helpers.Motion;

import java.awt.*;

/**
 * The main class for all the creatures on the game except the player (the player is a moving entity)
 */
public class Creature extends Entity {
    public Creature(Controller controller) {
    }

    @Override
    public void update(State state) {
    }

    @Override
    public Image getSprite() {
        return null;
    }
}
