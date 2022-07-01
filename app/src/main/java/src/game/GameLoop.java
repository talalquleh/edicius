package src.game;
import src.state.State;
import src.state.GameMenu;
import java.util.Timer;
import java.util.TimerTask;
import src.helpers.buttons.PauseButton;
public class GameLoop implements Runnable{

    public static final int UPDATES_PER_SECOND = 60;
    public static Game game;

    private boolean running = false;
    private final double updateRate = 1.0d/UPDATES_PER_SECOND;
    private Timer timer;
    private MyTimerTask myTimerTask;
    private long nextStatTime;
    private int fps, ups;
    public static int killedEnemiesCnt=0;
    public GameLoop(Game game){
        GameLoop.game = game;
        this.myTimerTask = new MyTimerTask(this);
        this.timer = new Timer();
    }


    @Override
    public void run() {
        running = true;
        timer.scheduleAtFixedRate(myTimerTask, 0, (long)(updateRate*1000));

        nextStatTime = System.currentTimeMillis() + 1000;

    }

    public void printState() {
        if(System.currentTimeMillis() > nextStatTime){
            System.out.println(String.format("FPS: %d, UPS: %d", fps, ups));
            fps = 0;
            ups = 0;
            nextStatTime = System.currentTimeMillis() + 1000;
        }
    }
    class MyTimerTask extends TimerTask {
        double accumulator = 0;
        long currentTime, lastUpdate;
        GameLoop gl;
        MyTimerTask(GameLoop gl)
        {
            this.gl = gl;
            lastUpdate = System.currentTimeMillis();
        }
        public void run()
        {
            if (gl.running) {
                 if(State.gameOver) {
                    gl.running=false;
                    this.cancel();
                    game.getDisplay().disableFrame();
                    State.gameOver=false;
                    new GameMenu(true,State.totalKills);
                    return;
                    };
                currentTime = System.currentTimeMillis();
                double lastRenderTimeInSeconds = (currentTime - lastUpdate) / 1000d;
                accumulator += lastRenderTimeInSeconds;
                lastUpdate = currentTime;

                if (accumulator >= gl.updateRate) {
                    while (accumulator >= gl.updateRate) {
                        gl.update();
                        accumulator -= gl.updateRate;
                    }
                    gl.render();
                }
                // gl.printState();
            }
            //Game.log("Current time: " + (currentTime / 1000) );
        }
    }
    /**
     * Calling the update function on the game class.
     */
    private void update() {
        if(!PauseButton.instance.isPaused){
            game.update();
            ups++;
        }
    }

    /**
     * Calling the render function on the game class.
     */
    private void render() {
        game.render();
        fps++;

    }


    public boolean getRunning(){return this.running;}
    public double getUpdateRate(){return  this.updateRate;}
    public  int getUps(){return  this.ups;}

}

