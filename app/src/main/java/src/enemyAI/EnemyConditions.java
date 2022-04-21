package src.enemyAI;

import src.entities.Enemy;
import src.state.State;

public interface EnemyConditions {
    boolean isMet(State state, Enemy enemy);
}
