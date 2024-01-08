package Main;
import Character.MerchantNPC;
import Clock.Clock;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Character.Player;
public class Crop implements MerchantPrice {
    private String cropName;
    private double purchasePrice;
    private double sellPrice;
    private int daysToGrow;
    private int daysGrown;
    private int currentDay;

    public int[] waterDay = new int[30];
    public int count = 0;
    public int worldX, worldY;

    public BufferedImage image;
    public GamePanel gp;

    public Crop(String name, double initPurchasePrice, double initSellPrice, int initDaysToGrow) {
        cropName = name;
        purchasePrice = initPurchasePrice;
        sellPrice = initSellPrice;
        daysToGrow = initDaysToGrow;
    }
    public Crop(Crop crop) {
        cropName = crop.getName();
        purchasePrice = crop.getPurchasePrice();
        sellPrice = crop.getSellPrice();
        daysToGrow = crop.getDaysToGrow();
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

    public void draw(Graphics2D g2,GamePanel gp){
        int screenX = worldX - gp.player.worldX + gp.player.screenX + gp.tileSize/4; //15 is to move to center

        int screenY = worldY - gp.player.worldY + gp.player.screenY + gp.tileSize/4;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            if (image != null) {
                g2.drawImage(image, screenX, screenY, gp.tileSize/2, gp.tileSize/2, null);
            }
        }
    }

}
