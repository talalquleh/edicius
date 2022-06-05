package src.enemyAI.EnemyState;

import src.controller.EnemyController;
import src.enemyAI.EnemyTransition;
import src.entities.Enemy;
import src.entities.Player;
import src.game.GameLoop;
import src.helpers.CollisionBox;
import src.helpers.Position;
import src.state.State;
import src.helpers.IntVector2D;
import src.tiles.Node;
import src.display.Renderer;
import src.helpers.Size;
import java.util.ArrayList;
import java.util.List;
import src.game.Game;
import java.awt.*;
public class Wonder extends EnemyState{
    private List<Position> targets;
    private Player player;
     private List<Node> path = null;
    private List<CollisionBox> mapCollisionBoxes;
    private CollisionBox enemyCollisionBox;

    //public Wonder(List<CollisionBox> mapCollisionBoxes, CollisionBox enemyCollisionBox)
    public Wonder() {
        super();
        this.targets = new ArrayList<>();
        this.player = GameLoop.game.getPlayer();
//        this.enemyCollisionBox = enemyCollisionBox;
//        this.mapCollisionBoxes = mapCollisionBoxes;
    }

    @Override
    protected EnemyTransition initializeTransition() {
        return new EnemyTransition("stand", ((state, currenEnemy)-> arrived(currenEnemy)));
    }

    public static Rectangle entityToTile(Rectangle r)
	{
		return new Rectangle(r.x / Game.SPRITE_SIZE, r.y / Game.SPRITE_SIZE,
				r.width / Game.SPRITE_SIZE + 1,
				r.height / Game.SPRITE_SIZE + 1);
	}

    @Override
    public void update(State state, Enemy currentEnemy, Position target) {
        //old code
    //     if(this.targets.isEmpty()){
    //         targets.add(state.getRandomPosition());
    //     }
    //     EnemyController controller = (EnemyController) currentEnemy.getController();
    //    // controller.moveToTarget(player.getPosition(), currentEnemy.getPosition(), mapCollisionBoxes, enemyCollisionBox);
    //     controller.moveToTarget(target, currentEnemy.getPosition());

    //     if(arrived(currentEnemy)){
    //         controller.stop();
    //     }
             if(this.targets.isEmpty()){
            targets.add(state.getRandomPosition());
        }
        EnemyController controller = (EnemyController) currentEnemy.getController();
        controller.moveToTarget(player.getPosition(), currentEnemy.getPosition());

        //System.out.println(player.getPosition().getX());
        //System.out.println(currentEnemy.getPosition().getX());
        Rectangle rect = entityToTile(Renderer.entityToPixel(player.getPosition(), player.getSize()));
        int midX = (int)(rect.getX() + rect.getWidth() / 2),
            midY = (int)(rect.getY() + rect.getHeight() / 2);
        rect = entityToTile(Renderer.entityToPixel(currentEnemy.getPosition(), new Size(16, 28)));
        int enemyMidX = (int)(rect.getX() + rect.getWidth() / 2),
                enemyMidY = (int)(rect.getY() + rect.getHeight() / 2);
        if (rect.getWidth() == 2 || rect.getHeight() == 2) {
            System.out.println(rect + " " + enemyMidX + " " + enemyMidY);
            System.out.println(Renderer.entityToPixel(player.getPosition(), new Size(16, 28)));
            controller.moveToTarget(new Position((enemyMidX) * Game.SPRITE_SIZE + Game.SPRITE_SIZE/2,(enemyMidY) * Game.SPRITE_SIZE + Game.SPRITE_SIZE/2), currentEnemy.getPosition());
            return;
        }

         path=Node.findPath(new IntVector2D(midX,midY),new IntVector2D(enemyMidX,enemyMidY),state);
//        path=Node.findPath(new IntVector2D(currentEnemy.getPosition().getX(),currentEnemy.getPosition().getY()),new IntVector2D(player.getPosition().getX(),player.getPosition().getY()));

        /*int[][] tiles = state.getGameMap().getTilesValues();
        String mapText = ""; //0, 0 is upper-left
        for (int i = 0; i < tiles[0].length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                boolean hasPath = false;
                if (path != null) {
                    for (Node n : path) {
                        if (n.tile.getY() == i & n.tile.getX() == j) {                        
                            hasPath = true; break;
                        }
                    }
                }
                if (midY == i && midX == j) mapText += "P";
                else if (enemyMidY == i && enemyMidX == j) mapText += "E";                
                else if (hasPath) mapText += "H";
                else mapText += tiles[j][i] != 0 ? "+" : " ";

            }
            mapText += "\n";
        }
        System.out.println(mapText);*/

        if(path!=null){
            if(path.size()>1){
                IntVector2D vec= path.get(1).tile;
                //System.out.println(path);
                controller.moveToTarget(new Position((vec.getX()) * Game.SPRITE_SIZE + Game.SPRITE_SIZE/2,(vec.getY()) * Game.SPRITE_SIZE + Game.SPRITE_SIZE/2), currentEnemy.getPosition());

            }
    }
    }

    private boolean arrived(Enemy currentEnemy) {
        return currentEnemy.getPosition().isInRangeOf(player.getPosition());
    }
}
