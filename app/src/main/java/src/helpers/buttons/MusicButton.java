package src.helpers.buttons;

import javax.sound.sampled.Clip;
import src.gfx.Assets;
public class MusicButton extends Button {

    public static MusicButton instance=new MusicButton(0,700-40,100,40);
    public Clip music_clip;
    private boolean isActive = false;
    //text for now
    public String btn_text="MUSIC OFF";

    public MusicButton(int x, int y,int width,int height) {
        super( x, y,width,height);
        this.setScaledImage("/sprites/btns/soundOn.png");
    }

    /**
     * play the music.
     */
    public void toggleMusic() {
        isActive = !isActive;
        if (isActive) {
            img = Assets.loadImage("/sprites/btns/soundOff.png");
            btn_text="MUSIC OFF";
            music_clip.loop(music_clip.LOOP_CONTINUOUSLY);
        } else {
            img = Assets.loadImage("/sprites/btns/soundOn.png");
            btn_text="MUSIC ON";
            music_clip.stop();
        }


    }
}
