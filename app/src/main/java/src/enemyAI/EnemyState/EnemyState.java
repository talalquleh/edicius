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

    /**
     * Initialize the transition state of the enemy.
     * 
     * @return Enemy Transition state.
     */
    protected abstract EnemyTransition initializeTransition();

    /**
     * Update the status of enemy.
     * 
     * @param state
     * @param currentEnemy
     * @param target
     */
    public abstract void update(State state, Enemy currentEnemy, Position target);

    /**
     * Determine whether an enemy should transit or not.
     * 
     * @param state
     * @param currentEnemy
     * @return whether an enemy should transit or not.
     */
    public boolean shouldTransition(State state, Enemy currentEnemy){
        return transition.shouldTransition(state, currentEnemy);
    }

    /**
     * 
     * @return the next transition state of the enemy.
     */
    public String getNextState(){
        return this.transition.getNextState();
    }
}
