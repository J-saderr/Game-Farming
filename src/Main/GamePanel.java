package Main;

import ItemSystem.UI;
import Tile.TileManager;

import javax.swing.*;
import java.awt.*;
import Character.*;
public class GamePanel extends JPanel implements Runnable{  //subclass of JPanel
    //Screen settings
    //FPs 60
    int fps = 60;
    final int originalTileSize = 16; //16x16 tile
    final int scale = 4; //size of character = 16x3 - change later
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16; // rong 16
    public final int maxScreenRow = 12; // dai 12
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public final int maxWorldCol = 60;
    public final int maxWorldRow = 60;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    //tile
    public TileManager tileManager = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    public Player player = new Player(this, keyH);
    public Collision collision = new Collision(this);
    Thread gameThread;
    //set default position - coordinates of player
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 10;
    public Entity obj[] = new Entity[10];
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Sound sound = new Sound();
    public int gameState;
    public int titleState = 0;
    public final int playerState = 1;
    public final int pauseState = 2;
    public final int characterState =4;
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(131,146,76));
        this.setDoubleBuffered(true); //all the drawing from component will be done in offscreen painting buffer
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void setupGame() {
        AssetSetter aSetter = null;
        //aSetter.setObject();
        gameState = titleState;

    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void stopMusic() {
        sound.stop();
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
        if (gameState == playerState) {
            player.update();
        }
        if (gameState == pauseState) {
            //nothing
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (gameState == titleState) {
            ui.draw(g2);
        } else {
            tileManager.draw(g2); //draw tile before player
            player.draw(g2);
            ui.draw(g2);
            g2.setColor(Color.white);
            g2.dispose(); // dispose of this graphics context and release any system resources that it is using -> to save memory

        }
    }
}
