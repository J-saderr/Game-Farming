package Main;

import ItemSystem.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import HouseLevel.House;

public class Entity {
    public GamePanel gp;
    final int originalTileSize = 16; //16x16 tile
    final int scale = 4; //size of character = 16x3 - change later
    public final int tileSize = originalTileSize * scale;
    public Entity(GamePanel gp){
        this.gp= gp;
    }
    public int worldX, worldY;
    public int speed = 4;
    public BufferedImage image;
    public Rectangle solidArea = new Rectangle(8, 16, 32, 32);
    public Rectangle solidAreaHouse = new Rectangle(250, 250, 250, 250);
    public boolean collisionOn = false;

    public boolean collision;
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public int solidDefaultX = 0;
    public int solidDefaultY = 0;
    public BufferedImage down1, down2, down3, up1, up2, up3, right1, right2, right3, left1, left2, left3;
    public BufferedImage house1, house2, house3, house4, house5;
    public BufferedImage doUp1, doUp2, doRight1, doRight2, doDown1, doDown2, doLeft1, doLeft2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public String name;
    public Entity currentTool;
    // Item attribute
    public String description = "";
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;

    //CHARACTER STATUS
    public int maxLife;
    public int life;
    public int houseLevel;
    public BufferedImage enbar, enbar0, moneybar;
    public String[] dialogues = new String[20];
    int dialogueIndex = 0;
    public int type;
    public final int type_player =0;
    public final int type_npc = 1;
    public final int type_watercan =2;
    public final int type_axe=3;
    public final int type_hoe=4;
    public final int type_plants = 5;
    public boolean doing = false;
    public int price;
    public int money;

    public BufferedImage setup (String imagePath) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(imagePath + ".png"));
            image = uTool.scaleImage(image, tileSize, tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public BufferedImage setuptool (String imagePath,int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(imagePath + ".png"));
            image = uTool.scaleImage(image, width,height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
    public void speak(){
        if(dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
        //MAKE NPC FACE TO PLAYER
        switch (gp.player.direction){
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "right":
                direction = "left";
                break;
            case "left":
                direction = "right";
                break;
        }
    }
    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
    }
    public void drawHouse(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (gp.houselv.houseLevel == 1) {
            g2.drawImage(house1, screenX, screenY, gp.tileSize*6, gp.tileSize*6, null);
        } else if (gp.houselv.houseLevel == 2) {
            g2.drawImage(house2, screenX, screenY, gp.tileSize*6, gp.tileSize*6, null);
        } else if (gp.houselv.houseLevel == 3) {
            g2.drawImage(house3, screenX, screenY, gp.tileSize*6, gp.tileSize*6, null);
        } else if (gp.houselv.houseLevel == 4) {
            g2.drawImage(house4, screenX, screenY, gp.tileSize*6, gp.tileSize*6, null);
        } else if (gp.houselv.houseLevel == 5) {
            g2.drawImage(house5, screenX, screenY, gp.tileSize*6, gp.tileSize*6, null);
        } else  {
            g2.drawImage(house5, screenX, screenY, gp.tileSize*6, gp.tileSize*6, null);
        }
    }
}