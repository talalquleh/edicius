package src.enemyAI.EnemyState;

import src.enemyAI.EnemyTransition;
import src.entities.Enemy;
import src.helpers.Position;
import src.state.State;

public abstract class EnemyState {

    private EnemyTransition transition;

    public EnemyState() {
        this.transition = initializeTransition();
    }

    protected abstract EnemyTransition initializeTransition();
    public abstract void update(State state, Enemy currentEnemy, Position target);

    public boolean shouldTransition(State state, Enemy currentEnemy){
        return transition.shouldTransition(state, currentEnemy);
    }

    public String getNextState(){
        return this.transition.getNextState();
    }
}
