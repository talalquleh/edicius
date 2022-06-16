package src.enemyAI;

import src.enemyAI.EnemyState.EnemyState;
import src.enemyAI.EnemyState.Stand;
import src.enemyAI.EnemyState.Wonder;
import src.entities.Enemy;
import src.helpers.CollisionBox;
import src.helpers.Position;
import src.state.State;


public class Brain {
    private EnemyState currentState;
    // private List<CollisionBox> mapCollisionBoxes;
    // private CollisionBox EnemyCollisionBox;
    // private boolean isShot;
//List<CollisionBox> mapCollisionBoxes, CollisionBox collisionBox
    public Brain() {
        transitionTo("stand");
//        this.EnemyCollisionBox = collisionBox;
//        this.mapCollisionBoxes = mapCollisionBoxes;
        // this.isShot = false;
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
                //currentState = new Wonder(mapCollisionBoxes, EnemyCollisionBox);
                currentState = new Wonder();
                return;
            case "stand":
            default:
                currentState = new Stand();
        }
    }
}
