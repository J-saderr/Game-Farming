package Character;

import Objects.SuperObjects;
import Tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{  //subclass of JPanel
    //Screen settings
    //FPs 60
    int fps = 60;
    final int originalTileSize = 16; //16x16 tile
    final int scale = 4; //size of character = 16x3 - change later
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16; // rong 16
    public final int maxScreenRow = 12; // dai 12
    final int screenWidth = tileSize * maxScreenCol;
    final int screenHeight = tileSize * maxScreenRow;

    public final int maxWorldCol = 30;
    public final int maxWorldRow = 20;
    public final int worldWidth = tileSize * maxWorldCol;

    public final int worldHeight = tileSize * maxWorldRow;
    //tile
    TileManager tile = new TileManager(this);

    KeyHandler keyH = new KeyHandler();
    public PlayerMove player = new PlayerMove(this, keyH);
    Thread gameThread;
    public SuperObjects obj[] = new SuperObjects[30];
    public AssetSetter aSetter = new AssetSetter(this);
    //set default position - coordinates of player
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 10;
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(131,146,76));
        this.setDoubleBuffered(true); //all the drawing from component will be done in offscreen painting buffer
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        aSetter.setObject();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = (float) 1000000000 /fps;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) { //update in4: char position and draw the screen with updated in4
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        //Tile
        tile.draw(g2); //draw tile before player
        //Object
        for(int i = 0; i < obj.length; i++) {
            if(obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }
        //Player
        player.draw(g2);
        g2.setColor(Color.white);
        g2.dispose(); // dispose of this graphics context and release any system resources that it is using -> to save memory


    }
}
