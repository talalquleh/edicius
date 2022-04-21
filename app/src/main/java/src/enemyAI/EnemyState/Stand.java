package src.enemyAI.EnemyState;

import src.enemyAI.EnemyTransition;
import src.entities.Enemy;
import src.game.GameLoop;
import src.helpers.Position;
import src.state.State;

public class Stand extends EnemyState {

    private int updatesGoing;
    @Override
    protected EnemyTransition initializeTransition() {
        return new EnemyTransition("wonder", ((state, currentEnemy) -> updatesGoing >= 3 * GameLoop.UPDATES_PER_SECOND));
    }

    @Override
    public void update(State state, Enemy currentEnemy, Position target) {
        this.updatesGoing++;
    }
}
