package enemyAI.EnemyState;

import controller.EnemiesController;
import enemyAI.EnemyTransition;
import entities.Enemies;
import entities.Player;
import game.Game;
import game.GameLoop;
import helpers.Position;
import state.State;

import java.util.ArrayList;
import java.util.List;

public class Wonder extends EnemyState{
    private List<Position> targets;
    private Player player;

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
    public void update(State state, Enemies currentEnemy) {
        if(this.targets.isEmpty()){
            targets.add(state.getRandomPosition());
        }
        EnemiesController controller = (EnemiesController) currentEnemy.getController();
        controller.moveToTarget(player.getPosition(), currentEnemy.getPosition());

        if(arrived(currentEnemy)){
            controller.stop();
        }
    }

    private boolean arrived(Enemies currentEnemy) {
        return currentEnemy.getPosition().isInRangeOf(player.getPosition());
    }
}
