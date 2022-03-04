package enemyAI;

import enemyAI.EnemyState.EnemyState;
import enemyAI.EnemyState.Stand;
import enemyAI.EnemyState.Wonder;
import entities.Enemy;
import state.State;

public class Brain {
    private EnemyState currentState;

    public Brain() {
        transitionTo("stand");
    }

    public void update(State state, Enemy currentEnemy){
        currentState.update(state, currentEnemy);
        if(currentState.shouldTransition(state, currentEnemy)){
            transitionTo(currentState.getNextState());
        }
    }

    private void transitionTo(String nextState) {
        switch (nextState){
            case "wonder":
                currentState = new Wonder();
                return;
            case "stand":
            default:
                currentState = new Stand();
        }
    }
}
