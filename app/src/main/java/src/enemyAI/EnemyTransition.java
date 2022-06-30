package src.enemyAI;

import src.entities.Enemy;
import src.state.State;

public class EnemyTransition {
    private String nextState;
    private EnemyConditions condition;

    public EnemyTransition(String nextState, EnemyConditions condition) {
        this.nextState = nextState;
        this.condition = condition;
    }

    /**
     * Check whether an enemy should change the transition status to the next status or not.
     * 
     * @param state
     * @param currentEnemy
     * @return whether an enemy should transit or not.
     */
    public boolean shouldTransition(State state, Enemy currentEnemy){
        return condition.isMet(state, currentEnemy);
    }

    public String getNextState() {
        return nextState;
    }
}
