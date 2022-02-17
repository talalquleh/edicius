package enemyAI;

import entities.Enemies;
import state.State;

public interface EnemyConditions {
    boolean isMet(State state, Enemies enemy);
}
