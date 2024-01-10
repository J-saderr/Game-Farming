package Main;
import Environment.EnvironmentManager;
import Environment.Sound;
import HouseLevel.House;
import ItemSystem.Entities.Crop.Carrot;
import ItemSystem.Entities.Crop.Potato;
import ItemSystem.Entities.Crop.Spinach;
import ItemSystem.Entities.Soil.notWateredSoil;
import Map.AssetSetter;
import Map.Collision;
import Map.TileManager;
import HouseLevel.Sleeping;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import Character.*;


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
    public KeyHandler keyH = new KeyHandler(this);
    public EventHandler eHandler = new EventHandler(this);
    public Player player = new Player(this, keyH);
    public Collision collision = new Collision(this);
    public Sleeping sleeping = new Sleeping(this);
    public House houselv = new House(this);
    public Money money = new Money(this);
    Thread gameThread;
    //set default position - coordinates of player
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 10;
    public Entity[] entities = new Entity[30];
    public Entity obj[] = new Entity[30];
    public Entity npc[] = new Entity[1];
    public Entity house[] = new Entity[1];
    public Entity entity;
    public AssetSetter aSetter = new AssetSetter(this);
    public EnvironmentManager eManager= new EnvironmentManager(this);
    ArrayList<Entity> entityList = new ArrayList<>();
    public UI ui = new UI(this);
    public Carrot carrot = new Carrot(this);
    public Potato potato = new Potato(this);
    public Spinach spinach = new Spinach(this);
    Sound sound = new Sound();
    public int currentDay = 1;
    public int gameState;
    public int titleState = 0;
    public final int playerState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState =4;
    public final int sleepState = 5;
    public final int houseState = 6;
    public final int cannotUpdateState = 7;
    public final int houselvState = 8;
    public final int tradeState = 9;
    public final int gameOverState = 10;
    notWateredSoil notWateredSoil = new notWateredSoil(this);
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(new Color(131,146,76));
        this.setDoubleBuffered(true); //all the drawing from component will be done in offscreen painting buffer
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void setupGame() {
        houselv.setDefault();
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setHouse();
        eManager.setUp();
        gameState = titleState;
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
        //playMusic(0);
    }
    public void resetSoil(){
        for(int i=0; i<=23 ; i++ ){
            if(obj[i].name == "wateredSoil"){
                obj[i].image = notWateredSoil.image;
                obj[i].name = "notWateredSoil";
                }
            }

    }

    public void resetPlant(){
        for (int i = 0 ; i<=23; i++){
            this.entities[i] = null;
        }
    }
    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    @Override
    public void run() {

        double drawInterval = (float) 1000000000 /fps;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long drawCount = 0;
        long timer = 0;


        while (gameThread != null) { //update in4: char position and draw the screen with updated in4

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;
            if (delta >=1 ){
                update();
                if (timer >= 100000000){
                    //noticed
                    //clock.increaseTime();
                    /*if (clock.getHour() == 23 && clock.getMinute() == 40){
                        for ( int i = 0 ; i<= 23; i++) {
                            player.checkWatering(i);
                            if(entities[i] != null) {
                                if (entities[i].cropName == "Carrot") {
                                    carrot.CarrotLogic(i);}
                                if (entities[i].cropName == "Potato") {
                                    potato.PotatoLogic(i);}
                                if (entities[i].cropName == "Spinach") {
                                    spinach.SpinachLogic(i);}
                            }
                        }
                        }*/
                    drawCount =0;
                    timer =0;
                }
                repaint();
                delta--;
                drawCount++;

            }
        }
    }

    public void reset() {
        player.setDefault();
        player.inventory.clear();
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setHouse();
        eManager.setUp();
        player.setItems();
        houselv.setDefault();
        resetPlant();
    }
    public void update() {
        if (gameState == playerState) {
            player.update();
            eManager.update();
            tileManager.update();
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
            for(int i = 0; i < obj.length; i++) {
                if(obj[i] != null) {
                    obj[i].draw(g2,this);
                }
            }
            for (int i = 0; i < npc.length; i++) {
                if(npc[i] != null) {
                    npc[i].draw(g2,this);
                }
            }
            //draw crops:
            for(int i = 0; i < entities.length; i++) {
                if(entities[i] != null ) {
                    entities[i].draw(g2, this);
                }
            }
            house[0].drawHouse(g2, this);
            player.draw(g2);
            eManager.draw(g2);
            ui.draw(g2);
            g2.setColor(Color.white);
            g2.dispose(); // dispose of this graphics context and release any system resources that it is using -> to save memory
        }
    }
}
