package entities.creatures;

import controller.Controller;
import entities.Entity;
import state.State;
import helpers.Motion;

import java.awt.*;

public class Creature extends Entity {
    private Controller controller;
    private Motion motion;


    public Creature(Controller controller) {
        this.controller = controller;
        this.motion = new Motion(2);
    }

    @Override
    public void update(State state) {
        motion.update(controller);
        position.apply(motion);
    }

    @Override
    public Image getSprite() {
        return null;
    }
}
