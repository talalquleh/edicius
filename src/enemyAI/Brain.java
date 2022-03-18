package enemyAI;

import enemyAI.EnemyState.EnemyState;
import enemyAI.EnemyState.Stand;
import enemyAI.EnemyState.Wonder;
import entities.Enemy;
import helpers.CollisionBox;
import state.State;

import java.util.List;

public class Brain {
    private EnemyState currentState;
    private List<CollisionBox> mapCollisionBoxes;
    private CollisionBox EnemyCollisionBox;
//List<CollisionBox> mapCollisionBoxes, CollisionBox collisionBox
    public Brain() {
        transitionTo("stand");
//        this.EnemyCollisionBox = collisionBox;
//        this.mapCollisionBoxes = mapCollisionBoxes;
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
                //currentState = new Wonder(mapCollisionBoxes, EnemyCollisionBox);
                currentState = new Wonder();
                return;
            case "stand":
            default:
                currentState = new Stand();
        }
    }
}
