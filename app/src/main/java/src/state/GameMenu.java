package src.state;

import src.game.Game;
import src.game.GameLoop;
import src.gfx.Assets;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GameMenu extends JPanel implements MouseListener {

    protected Rectangle startGame;
    protected Rectangle exitGame;
    private JFrame Frame;
    private Graphics2D g2d;
    public boolean isPlayerDead=false;
    public int killCnt=0;
    public GameMenu(Boolean isPlayerDead,int killCnt) {
        this.isPlayerDead = isPlayerDead;
        this.killCnt=killCnt;
        Frame = new JFrame();
        Frame.setSize(700, 700);
        setPreferredSize(new Dimension(700, 700));
        startGame = new Rectangle(100, 150, 500, 100, "NEW GAME");
        exitGame = new Rectangle(startGame.x, startGame.y + startGame.height + 20, 500, 100, "EXIT");
        this.addMouseListener(this);
        Frame.add(this);
        Frame.setLocationRelativeTo(null);
        Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Frame.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        g2d = (Graphics2D) g;
        if(this.isPlayerDead){
            g2d.drawImage(Assets.loadImage("/sprites/gameOverBG.jpg"), 0, 0, 700, 700, this);
        }
        else{
            g2d.drawImage(Assets.loadImage("/sprites/btns/gameBackground.png"), 0, 0, 700, 700, this);
        }
        g2d.setPaint(new Color(40, 41, 60));
        g2d.fillRect(startGame.x, startGame.y, startGame.width, startGame.height);
        g2d.fillRect(exitGame.x, exitGame.y, exitGame.width, exitGame.height);
        g2d.setPaint(Color.white);
        g2d.drawString(startGame.text, startGame.getStringX() - 30, startGame.getStringY());
        g2d.drawString(exitGame.text, exitGame.getStringX() - 20, exitGame.getStringY());
        if(this.isPlayerDead){
            g2d.setColor(Color.red);
            g2d.setFont(new Font("Monospaced", Font.BOLD, 50));
            FontMetrics metrics3 = g2d.getFontMetrics(g2d.getFont());
            g2d.drawString("Total Kills: "+this.killCnt, 170, 700 / 6);
        }

    }

    private class Rectangle {

        public int x;
        public int y;
        public int width;
        public int height;
        private String text;

        public Rectangle(int x, int y, int width, int height, String text) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.text = text;
        }

        private int getStringX() {
            return this.x + this.width / 2;
        }

        private int getStringY() {
            return this.y + this.height / 2;
        }

        public boolean insideOption(int x, int y) {
            return (x >= this.x && x <= this.x + this.width) && (y >= this.y && y <= this.y + this.height);
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        if (startGame.insideOption(e.getX(), e.getY())) {
            //startingGame
            Frame.dispose();
            new Thread(new GameLoop(new Game(700, 700))).start();

        } else if (exitGame.insideOption(e.getX(), e.getY())) {
            System.out.println("exit");
            Frame.dispose();
        }

    }


    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

}
