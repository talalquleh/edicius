package enemyAI.EnemyState;

import controller.EnemyController;
import enemyAI.EnemyTransition;
import entities.Enemy;
import entities.Player;
import game.GameLoop;
import helpers.Position;
import helpers.Vector2D;
import state.State;
import tiles.Node;

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
         path=Node.findPath(new Vector2D(player.getPosition().getX(),player.getPosition().getY()),new Vector2D(currentEnemy.getPosition().getX(),currentEnemy.getPosition().getY()),state);
//        path=Node.findPath(new Vector2D(currentEnemy.getPosition().getX(),currentEnemy.getPosition().getY()),new Vector2D(player.getPosition().getX(),player.getPosition().getY()));
        if(path!=null){
            if(path.size()>0){
                Vector2D vec= path.get(path.size()-1).tile;
                controller.moveToTarget(currentEnemy.getPosition(),new Position(vec.getX(),vec.getY()));

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
