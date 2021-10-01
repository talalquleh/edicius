package game;
public class GameLoop implements Runnable{

    private Game game;

    private boolean running = false;
    private final double updateRate = 1.0d/60.0d;

    private long nextStatTime;
    private int fps, ups;

    public GameLoop(Game game){
        this.game = game;
    }

    @Override
    public void run() {
        running = true;
        double accumulator = 0;
        long currenTime, lastUpdate = System.currentTimeMillis();

        nextStatTime = System.currentTimeMillis() + 1000;

        while(running){
            currenTime = System.currentTimeMillis();
            double lastRenderTimeInSeconds = (currenTime - lastUpdate) / 1000d;
            accumulator += lastRenderTimeInSeconds;
            lastUpdate = currenTime;

            if(accumulator >= updateRate) // if it is updated atleaset once then we do a render
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
    
    private void update() {
        game.update();
        ups++;
    }
    
    private void render() {
        game.render();
        fps++;

    }
}
