package src.gfx;

import java.awt.Image;
import java.awt.image.BufferedImage;

import src.game.Game;
import src.helpers.Direction;

public class Animation {

    private SpriteSet spriteSet;
    private BufferedImage currentAnimationSheet;

    private int currentFrameTime;
    private int updatesPerFrame;
    private int frameIndex;
    private int directionIndex;

    public Animation(SpriteSet spriteSet) {
        this.updatesPerFrame = 20;
        this.currentFrameTime = 0;
        this.frameIndex = 0;
        this.directionIndex =0;
        this.spriteSet = spriteSet;
        this.playAnimation("stand");
    }

    /**
     * Cutting the original player image into pieces according the size of the player! (NOTE: this method is exclusive for the player
     * @return supImage
     */
    public Image getSprite() {
        return this.currentAnimationSheet.getSubimage(
                frameIndex * Game.SPRITE_SIZE,
                directionIndex * Game.SPRITE_SIZE,
                Game.SPRITE_SIZE, Game.SPRITE_SIZE);
    }

    /**
     * Updating the direction of the moving entity according the animation row which was declared on the Direction enum!
     * @param direction
     */
    public void update(Direction direction) {
        this.currentFrameTime++;
        directionIndex = direction.getAnimationRow();
        if(this.currentFrameTime >= this.updatesPerFrame) {
            this.currentFrameTime =0;
            this.frameIndex++;

            if(this.frameIndex >= this.currentAnimationSheet.getWidth() / Game.SPRITE_SIZE ) {
                this.frameIndex = 0;
            }
        }
    }

    public void playAnimation(String name) {
        this.currentAnimationSheet = (BufferedImage) this.spriteSet.get(name);
    }
}
