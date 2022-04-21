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

    public boolean shouldTransition(State state, Enemy currentEnemy){
        return condition.isMet(state, currentEnemy);
    }

    public String getNextState() {
        return nextState;
    }
}