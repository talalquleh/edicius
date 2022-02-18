package enemyAI;

import entities.Enemy;
import state.State;

public interface EnemyConditions {
    boolean isMet(State state, Enemy enemy);
}
