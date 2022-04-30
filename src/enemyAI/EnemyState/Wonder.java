package enemyAI.EnemyState;

import controller.EnemyController;
import display.Renderer;
import enemyAI.EnemyTransition;
import entities.Enemy;
import entities.Player;
import game.Game;
import game.GameLoop;
import helpers.IntVector2D;
import helpers.Position;
import helpers.Vector2D;
import state.State;
import tiles.Node;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Wonder extends EnemyState{
    private List<Position> targets;
    private Player player;
    private List<Node> path = null;
    public Wonder() {
        super();
        this.targets = new ArrayList<>();
        this.player = GameLoop.game.getPlayer();
    }

    @Override
    protected EnemyTransition initializeTransition() {
        return new EnemyTransition("stand", ((state, currenEnemy)-> arrived(currenEnemy)));
    }

    @Override
    public void update(State state, Enemy currentEnemy) {
        if(this.targets.isEmpty()){
            targets.add(state.getRandomPosition());
        }
        EnemyController controller = (EnemyController) currentEnemy.getController();
        controller.moveToTarget(player.getPosition(), currentEnemy.getPosition());
//        System.out.println(player.getPosition().getX());
//        System.out.println(currentEnemy.getPosition().getX());
        Rectangle rect = Renderer.entityToTile(Renderer.entityToPixel(player.getPosition(), player.getSize()));
        int midX = (int)(rect.getX() + rect.getWidth() / 2),
            midY = (int)(rect.getY() + rect.getHeight() / 2);
        rect = Renderer.entityToTile(Renderer.entityToPixel(currentEnemy.getPosition(), currentEnemy.getSize()));
        int enemyMidX = (int)(rect.getX() + rect.getWidth() / 2),
                enemyMidY = (int)(rect.getY() + rect.getHeight() / 2);

         path=Node.findPath(new IntVector2D(midX,midY),new IntVector2D(enemyMidX,enemyMidY),state);
//        path=Node.findPath(new IntVector2D(currentEnemy.getPosition().getX(),currentEnemy.getPosition().getY()),new IntVector2D(player.getPosition().getX(),player.getPosition().getY()));
        if(path!=null){
            if(path.size()>1){
                IntVector2D vec= path.get(1).tile;
                System.out.println(path);
                controller.moveToTarget(new Position((vec.getX()) * Game.SPRITE_SIZE,(vec.getY()) * Game.SPRITE_SIZE), currentEnemy.getPosition());

            }
        }
//            System.out.println("can't find path to player");
////            System.exit(1);
//        }
        if(arrived(currentEnemy)){
            controller.stop();
        }
    }

    private boolean arrived(Enemy currentEnemy) {
        return currentEnemy.getPosition().isInRangeOf(player.getPosition());
    }
}
