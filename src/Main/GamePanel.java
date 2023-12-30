package Main;

import Clock.Clock;
import ItemSystem.UI;
import Object.Crop.Carrot;
import Object.Crop.Potato;
import Object.Crop.Spinach;
import Tile.TileManager;
import javax.swing.*;
import java.awt.*;
import Character.*;
import Object.SuperObject;


public class GamePanel extends JPanel implements Runnable{  //subclass of JPanel
    //Screen settings
    //FPs 60
    int fps = 60;
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3; //size of character = 16x3 - change later
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
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
    public SuperObject[] obj = new SuperObject[30];
    public Crop[] crops = new Crop[30];
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public Carrot carrot = new Carrot(this);
    public Potato potato = new Potato(this);
    public Spinach spinach = new Spinach(this);
    Sound sound = new Sound();
    Clock clock = new Clock(this);
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
        aSetter.setObject();
        carrot.setCarrotImage();
        potato.setPotatoImage();
        spinach.setSpinachImage();

        gameState = titleState;
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
        playMusic(0);
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
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;


        while (gameThread != null) { //update in4: char position and draw the screen with updated in4

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;
            if (delta >=1 ){
                update();
                if (timer >= 1000000000){
                    //noticed
                    clock.increaseTime();
                    drawCount =0;
                    timer =0;
                }
                repaint();
                delta--;
                drawCount++;

            }



        }
    }
    public void update() {
        if (gameState == playerState) {
            player.update();
            tileManager.update();
            carrot.update();
            potato.update();
            spinach.update();
            // noticed
            //clock.increaseTime();
        }
        if (gameState == pauseState) {
            //nothing
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (gameState == titleState) {
            ui.draw(g2);
        } else {
            tileManager.draw(g2); //draw tile before player
            //draw crops:
            for(int i = 0; i < crops.length; i++) {
                if(crops[i] != null ) {
                    crops[i].draw(g2, this);
                }
            }
            player.draw(g2);
            for(int i = 0; i < obj.length; i++) {
                if(obj[i] != null) {
                    obj[i].draw(g2, this);
                }
            }
            ui.draw(g2);
            clock.drawTime(g2);
            g2.setColor(Color.white);
            g2.dispose(); // dispose of this graphics context and release any system resources that it is using -> to save memory
        }
    }
}
