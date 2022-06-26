package src.enemyAI;

import src.enemyAI.EnemyState.EnemyState;
import src.enemyAI.EnemyState.Stand;
import src.enemyAI.EnemyState.Wonder;
import src.entities.Enemy;
import src.state.State;


public class Brain {
    private EnemyState currentState;
    public Brain() {
        transitionTo("stand");
    }


    public void update(State state, Enemy currentEnemy){
        currentState.update(state, currentEnemy, state.getPlayer().getPosition());

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
