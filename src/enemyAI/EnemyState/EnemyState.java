package enemyAI.EnemyState;

import enemyAI.EnemyTransition;
import entities.Enemies;
import state.State;

public abstract class EnemyState {

    private EnemyTransition transition;

    public EnemyState() {
        this.transition = initializeTransition();
    }

    protected abstract EnemyTransition initializeTransition();
    public abstract void update(State state, Enemies currentEnemy);

    public boolean shouldTransition(State state, Enemies currentEnemy){
        return transition.shouldTransition(state, currentEnemy);
    }

    public String getNextState(){
        return this.transition.getNextState();
    }
}
