import game.*;

public class Launcher {
    public static void main(String[] args){
        new Thread(new GameLoop(new Game(700, 700))).start();
    }
}