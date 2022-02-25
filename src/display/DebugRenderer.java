package display;

import gfx.Camera;
import helpers.CollisionBox;
import state.State;

import java.awt.*;

public class DebugRenderer {
    public void render(State state, Graphics graphics) {
        Camera camera = state.getCamera();
        state.getGameObjects().stream()
                .map(entity -> entity.getCollisionBox())
                .forEach( collisionBox -> drawCollisionBox(collisionBox, graphics, camera) );
    }

    private void drawCollisionBox(CollisionBox box, Graphics g, Camera camera){
        g.setColor(Color.red);
        g.drawRect(
                (int) box.getBounds().getX() - camera.getPosition().intX(),
                (int) box.getBounds().getY() - camera.getPosition().intY(),
                (int) box.getBounds().getWidth(),
                (int) box.getBounds().getHeight()
                );
    }
}