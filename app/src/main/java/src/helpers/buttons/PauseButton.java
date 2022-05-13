package src.helpers.buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class PauseButton extends Button {
    public static PauseButton instance = new PauseButton(MusicButton.instance.x + MusicButton.instance.width + 10, MusicButton.instance.y, 100, 40);
    //text for now
    public String btn_text = "PAUSE";
    public boolean isPaused = false;

    public PauseButton(int x, int y, int width, int height) {
        super(x, y, width, height);
    }


    public void togglePause() {
        isPaused = !isPaused;
        if (isPaused) {
            this.btn_text = "RESUME";

        } else {
            this.btn_text = "PAUSE";
        }


    }
}
