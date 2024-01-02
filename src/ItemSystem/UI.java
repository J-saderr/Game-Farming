package ItemSystem;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

import Energy.EnergyBar;
import Main.Entity;
import Main.GamePanel;

import static java.awt.Color.white;


public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_40, arial_80B;
    public String message = "";
    public boolean messageOn = false;
    int messageCount = 0;
    public boolean gameFinished = false;
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("0.00");
    public int slotCol =0;
    public int slotRow =0;
    public int commandNum = 0;
    BufferedImage energy, energybar0;
    public String currentDialogue = "";
    int counter = 0;


    public UI(GamePanel gp) {
        this.gp =gp;
        //Font chu trong game
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        Entity energybar = new EnergyBar(gp);
        energy = energybar.enbar;
        energybar0 = energybar.enbar0;

    }
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        if (gp.gameState == gp.playerState) {
            drawPlayerEnergy();
        }
        if (gp.gameState == gp.pauseState) {
            drawPlayerEnergy();
            drawPauseScreen();
        }
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }
        if (gp.gameState == gp.characterState) {
            drawPlayerEnergy();
            drawCharacterScreen();
            drawInventory();
        }
        if (gp.gameState == gp.sleepState) {
            drawSleepScreen();
            drawPlayerEnergy();
        }
        if (gp.gameState == gp.houseState) {
            drawPlayerEnergy();
            drawSleepOrUpdate();
            if (!gp.keyH.canSleep) {drawCannotSleep();}
        }
        if (gp.gameState == gp.houselvState) {
            drawPlayerEnergy();
            drawYourHouseLevel();
        }
        if (gp.gameState == gp.cannotUpdateState) {
            drawPlayerEnergy();
            drawYourHouseLevel();
            drawCannotUpdate();
        }
    }
    public int getXforCenteredText ( String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }

    //title-screen
    public void drawTitleScreen() {
        // title-name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,70F));
        String text = "<BetterFarming>";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 3;
        // shadow-color
        g2.setColor(Color.BLACK);
        g2.drawString(text, x+6, y+6);
        // main-color
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);
        // character-image
        x = gp.screenHeight/2 - (gp.tileSize*2)/2;
        y += gp.tileSize*2;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
        g2.drawImage(gp.player.up2, x + 200, y, gp.tileSize*2, gp.tileSize*2, null);

        // menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));

        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize*3.5;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "LOAD GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "QUIT GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 2) {
            g2.drawString(">", x-gp.tileSize, y);
        }
    }
    // paused-screen
    public void drawPauseScreen() {
        String text = "GAME PAUSE";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;
        g2.drawString(text,x,y);
    }

    // house-and-sleep
    public void drawSleepOrUpdate() {

        //Description Frame
        int dFrameX = gp.tileSize - 30 ;
        int dFrameY = gp.tileSize + 20;
        int dFrameWidth = gp.tileSize *6;
        int dFrameHeight = gp.tileSize*3;
        drawSubWindow(dFrameX,dFrameY, dFrameWidth, dFrameHeight);

        //Draw description text
        int textX = dFrameX + 20;
        int textY = dFrameY + 40;
        g2.setFont(g2.getFont().deriveFont(20F));
        g2.drawString("Choose an action:", textX, textY);
        g2.drawString("1. Sleep (press Enter)", textX, textY+25);
        g2.drawString("2. Update House (press U)", textX, textY+50);
    }
    private void drawSleepScreen() {
        counter++;
        if (counter < 120) {
            gp.eManager.lighting.filterAlpha += 0.01f;
            if (gp.eManager.lighting.filterAlpha > 1f) {
                gp.eManager.lighting.filterAlpha = 1f;
            }
        }
        if (counter >= 120) {
            gp.eManager.lighting.filterAlpha -= 0.1f;
            if (gp.eManager.lighting.filterAlpha <= 0f) {
                gp.eManager.lighting.filterAlpha = 0f;
                counter = 0;
                gp.eManager.lighting.dayState = gp.eManager.lighting.day;
                gp.gameState = gp.playerState;
            }
        }
    }
    public void drawCannotSleep() {

        //Description Frame
        int dFrameX = gp.tileSize - 30 ;
        int dFrameY = gp.tileSize + 180;
        int dFrameWidth = gp.tileSize *6;
        int dFrameHeight = gp.tileSize*2;
        drawSubWindow(dFrameX,dFrameY, dFrameWidth, dFrameHeight);

        //Draw description text
        int textX = dFrameX + 20;
        int textY = dFrameY + 40;
        g2.setFont(g2.getFont().deriveFont(20F));
        g2.drawString("Cannot sleep at day!", textX, textY);
        g2.drawString("Press O to Exit", textX, textY+ 25);
    }

    public void drawCannotUpdate() {

        //Description Frame
        int dFrameX = gp.tileSize - 30 ;
        int dFrameY = gp.tileSize + 200;
        int dFrameWidth = gp.tileSize *6;
        int dFrameHeight = gp.tileSize*2;
        drawSubWindow(dFrameX,dFrameY, dFrameWidth, dFrameHeight);

        //Draw description text
        int textX = dFrameX + 20;
        int textY = dFrameY + 40;
        g2.setFont(g2.getFont().deriveFont(20F));
        g2.drawString("Not enough money", textX, textY);
        g2.drawString("Press O to Exit", textX, textY+ 25);

    }

    public void drawYourHouseLevel() {
        //Description Frame
        int dFrameX = gp.tileSize - 30 ;
        int dFrameY = gp.tileSize + 30;
        int dFrameWidth = gp.tileSize *6 + 60;
        int dFrameHeight = gp.tileSize*3 + 20;
        drawSubWindow(dFrameX,dFrameY, dFrameWidth, dFrameHeight);

        //Draw description text
        int textX = dFrameX + 20;
        int textY = dFrameY + 40;
        g2.setFont(g2.getFont().deriveFont(20F));
        if (gp.houselv.houseLevel < 6) {
            g2.drawString("Your House Level is " + gp.houselv.houseLevel, textX, textY);
            g2.drawString("Your money is " + gp.money.amount, textX, textY + 25);
            g2.drawString("Press Enter to update House Level.", textX, textY + 50);
            g2.drawString("Press O to Exit", textX, textY + 75);
        } else {
            g2.drawString("Your House Level is 5" , textX, textY);
            g2.drawString("You've reached max level!", textX, textY + 25);
            g2.drawString("Press O to Exit", textX, textY + 50);
        }
    }

    // energy bar
    public void drawPlayerEnergy() {
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;


        g2.drawImage(energybar0, x-10, y-45, null);

        while (i < gp.player.life) {
            g2.drawImage(energy, x, y, null);
            i++;
            x += gp.tileSize/2.1;
        }
    }

    // dialogues
    public void drawDialogueScreen()  {
        //WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 5;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,32F));
        x += gp.tileSize;
        y += gp.tileSize;
        //DISPLAY MULTIPLES LINES
        for(String line: currentDialogue.split("\n")) {
            g2.drawString(line,x,y);
            y += 40;
        }
    }

    // inventory
    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(164, 76, 69);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(243, 229, 215);
        g2.setColor(c);
        g2.setStroke (new BasicStroke (5));
        g2.drawRoundRect (x+5, y+5, width-10, height-10, 25, 25);
    }
    public void drawInventory(){
        int frameX = gp.tileSize * 9;
        int frameY= gp.tileSize;
        int frameWidth = gp.tileSize * 6;
        int frameHeight = gp.tileSize * 5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
    // SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
    // DRAW PLAYER'S ITEMS
        for(int i=0; i < gp.player.inventory.size(); i++) {
            if(gp.player.inventory.get(i) == gp.player.currentTool){
                g2.setColor(new Color(240,190,90));
                g2.fillRoundRect(slotX,slotY,gp.tileSize,gp.tileSize,10,10);
            }
            g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);
            slotX += gp.tileSize;
            if (i ==4 || i == 9 || i == 14) {
                slotX = slotXstart;
                slotY += gp.tileSize;
            }
        }

    // CURSOR
        int cursorX = slotXstart + (gp.tileSize * slotCol);
        int cursorY = slotYstart + (gp.tileSize * slotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

    // DRAW CURSOR
        Color c = new Color(243, 229, 215);
        g2.setColor(c);
        g2.setStroke (new BasicStroke(3));
        g2.drawRoundRect (cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

    //Description Frame
        int dFrameX = frameX;
        int dFrameY = frameY +frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.tileSize*3;
        drawSubWindow(dFrameX,dFrameY, dFrameWidth, dFrameHeight);

    //Draw description text
        int textX = dFrameX + 20;
        int textY = dFrameY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(28F));
        
        int itemIndex = getItemIndexOnSlot();
        
        if(itemIndex < gp.player.inventory.size()){
            g2.drawString(gp.player.inventory.get(itemIndex).description, textX, textY);
            textY += 32;
        }
    }
    public int getItemIndexOnSlot(){
        int itemIndex = slotCol + (slotRow*5);
        return  itemIndex;
    }
    public void drawCharacterScreen(){
        int frameX = gp.tileSize * 9;
        int frameY= gp.tileSize;
        int frameWidth = gp.tileSize * 6;
        int frameHeight = gp.tileSize * 5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

}

