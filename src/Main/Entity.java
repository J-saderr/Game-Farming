package Main;

import Clock.Clock;
import ItemSystem.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Entity implements MerchantPrice {
    public GamePanel gp;
    final int originalTileSize = 16; //16x16 tile
    final int scale = 4; //size of character = 16x3 - change later
    public final int tileSize = originalTileSize * scale;
    public Entity(GamePanel gp){
        this.gp= gp;
    }
    public int worldX, worldY;
    public int speed ;

    public BufferedImage image;
    public Rectangle solidArea;
    public boolean collisionOn = false;

    public int cropPeriod = 0;

    public boolean collision;
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public int solidDefaultX, solidDefaultAreaY;
    public BufferedImage down1, down2, down3, up1, up2, up3, right1, right2, right3, left1, left2, left3;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public String name;
    // Item attribute
    public String description = "";
    public int type;
    public final int type_watercan = 1;
    public final int type_axe = 2;
    public final int type_hoe = 3;
    public boolean doing = false;
    public String cropName;
    private double purchasePrice;
    private double sellPrice;
    private int daysToGrow;
    private int daysGrown;
    private int currentDay;
    public final int type_carrot = 4;
    public final int type_carrot_mature = 45;
    public final int type_potato = 5;
    public final int type_potato_mature = 55;
    public final int type_spinach = 6;
    public final int type_spinach_mature = 65;
    public int[] waterDay = new int[30];
    public int[] count = new int[30];
    public BufferedImage doUp1, doUp2, doRight1, doRight2, doDown1, doDown2, doLeft1, doLeft2;
    public Entity(String name, double initPurchasePrice, double initSellPrice, int initDaysToGrow) {
        cropName = name;
        purchasePrice = initPurchasePrice;
        sellPrice = initSellPrice;
        daysToGrow = initDaysToGrow;
    }
    public Entity(Entity entity) {
        cropName = entity.getName();
        purchasePrice = entity.getPurchasePrice();
        sellPrice = entity.getSellPrice();
        daysToGrow = entity.getDaysToGrow();
        daysGrown = 0;
    }
    public boolean canHarvest() {
        if (daysGrown >= daysToGrow) {
            return true;
        }
        return false;
    }
    public void grow() {
        if (getDaysLeftToGrow() > 0) {
            daysGrown++;
        }
    }
    public void tend(double daysToIncrease) {
        daysGrown += daysToIncrease;
        if (getDaysLeftToGrow() < 0) {
            daysGrown = daysToGrow;
        }
    }
    public void setCurrentGrown() {
        this.currentDay = Clock.getDay();
    }
    public void setDaysGrown() {
        this.daysGrown = Clock.getDay();
    }
    public int getDaysPass() {

        return currentDay - daysGrown;
    }
    public double getPurchasePrice() {
        return purchasePrice;
    }
    public double getSellPrice() {
        return sellPrice;
    }
    public String getName() {
        return cropName;
    }
    public int getDaysToGrow() {
        return daysToGrow;
    }
    public int getDaysLeftToGrow() {
        return daysToGrow - daysGrown;
    }

    public int getDaysGrown(){
        return daysGrown;
    }



    public void update() {

    }
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
    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            g2.drawImage(image, screenX, screenY, gp.tileSize/2, gp.tileSize/2, null);
        }
    }
}
