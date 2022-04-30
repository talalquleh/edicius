package enemyAI.EnemyState;

import enemyAI.EnemyTransition;
import entities.Enemy;
import state.State;

public class Stand extends EnemyState {

    private int updatesGoing;
    @Override
    protected EnemyTransition initializeTransition() {
        return new EnemyTransition("wonder", ((state, currentEnemy) -> updatesGoing >= state.getTime().getUpdatesFromSeconds(3)));
    }

    @Override
    public void update(State state, Enemy currentEnemy) {
        this.updatesGoing++;
    }
}