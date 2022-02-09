package game;
public class GameLoop implements Runnable{

    public static final int UPDATES_PER_SECOND = 60;
    private Game game;

    private boolean running = false;
    private final double updateRate = 1.0d/UPDATES_PER_SECOND;

    private long nextStatTime;
    private int fps, ups;

    public GameLoop(Game game){
        this.game = game;
    }


    @Override
    public void run() {
        running = true;
        double accumulator = 0;
        long currentTime, lastUpdate = System.currentTimeMillis();

        nextStatTime = System.currentTimeMillis() + 1000;

        while(running){
            currentTime = System.currentTimeMillis();
            double lastRenderTimeInSeconds = (currentTime - lastUpdate) / 1000d;
            accumulator += lastRenderTimeInSeconds;
            lastUpdate = currentTime;

            if(accumulator >= updateRate)
            {
                while(accumulator >= updateRate){
                    update();
                    accumulator -= updateRate;
                }
                render();
            }
            printState();
        }
    }
    
    private void printState() {
        if(System.currentTimeMillis() > nextStatTime){
            System.out.println(String.format("FPS: %d, UPS: %d", fps, ups));
            fps = 0;
            ups = 0;
            nextStatTime = System.currentTimeMillis() + 1000; 
        }
    }

    /**
     * Calling the update function on the game class.
     */
    private void update() {
        game.update();
        ups++;
    }

    /**
     * Calling the render function on the game class.
     */
    private void render() {
        game.render();
        fps++;

    }
}
