package ItemSystem;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

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

    public UI(GamePanel gp) {
        this.gp =gp;
        //Font chu trong game
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        arial_80B = new Font("Arial", Font.BOLD, 80);

    }
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(arial_40);
        g2.setColor(Color.white);
        // title-state
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        // game-state
        if (gp.gameState == gp.playerState) {
        }
        // pause-state
        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }
        // character-state
        if (gp.gameState == gp.characterState) {
            drawCharacterScreen();
            drawInventory();
        }
    }
    public void drawTitleScreen() {
        // title-name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
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
    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0,0,0,210);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(255,255,255);
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
        g2.setColor(Color.white);
        g2.setStroke (new BasicStroke(3));
        g2.drawRoundRect (cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);
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
    //Display game paused
    public void drawPauseScreen() {
        String text = "GAME PAUSE";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;
        g2.drawString(text,x,y);
    }

    public int getXforCenteredText ( String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}

