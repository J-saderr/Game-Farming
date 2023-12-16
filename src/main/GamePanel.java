package main;
import Entity.PlayerMove;
import Entity.KeyHandler;
import Entity.entity;
import object.SuperObject;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{  //subclass of JPanel
    //Screen settings
    //FPs 60
    int fps = 60;
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3; //size of character = 16x3 - change later
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16; // rong 16
    public final int maxScreenRow = 12; // dai 12
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    Thread gameThread;
    //set default position - coordinates of player
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 10;
    //Checking Collision
    public Collision collide = new Collision(this);
    //entity and object
    KeyHandler keyH = new KeyHandler(this);
    public entity npc[] = new entity[10];
    public PlayerMove player = new PlayerMove(this, keyH);
    public SuperObject obj[] = new SuperObject[10]; //import 10 obj
    public AssetSetter aSetter = new AssetSetter(this);
    //GAME STATE
    public int gameState;
    public final int playerState = 1;
    public final int pauseState = 2;
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); //all the drawing from component will be done in offscreen painting buffer
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void setupGame() {
        AssetSetter aSetter = null;
        //aSetter.setObject();
        aSetter.setNPC();
        gameState = playerState;


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
        if (gameState == playerState) {
            //Player
            player.update();
            for (int i = 0; i < npc.length; i++){
                if(npc[i] != null) {
                    npc[i].update();
                }
            }
        }
        if (gameState == pauseState) {
            //nothing
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        //tile.draw(g2); //draw tile before player
        player.draw(g2);
        g2.dispose(); // dispose of this graphics context and release any system resources that it is using -> to save memory
        //draw Object

        //draw NPC
        for (int i = 0; i < npc.length; i++) {
            if(npc[i] != null) {
                npc[i].draw(g2);
            }
        }

    }
}
