package enemyAI;

import enemyAI.EnemyState.EnemyState;
import enemyAI.EnemyState.Stand;
import enemyAI.EnemyState.Wonder;
import entities.Enemies;
import state.State;

public class Brain {
    private EnemyState currentState;

    public Brain() {
        transitionTo("stand");
    }
//public Brain(EnemyState currentState) {
    //   this.currentState = currentState;
    //}

    public void update(State state, Enemies currentEnemy){
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
