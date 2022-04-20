package enemyAI.EnemyState;

import enemyAI.EnemyTransition;
import entities.Enemy;
import game.GameLoop;
import helpers.Position;
import state.State;

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
