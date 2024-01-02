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
    public int playerSlotCol =0;
    public int playerSlotRow =0;
    public int npcSlotCol =0;
    public int npcSlotRow =0;
    public int commandNum = 0;
    public int subState = 0;
    BufferedImage energy, energybar0;
    public String currentDialogue = "";
    public Entity npc;


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
            drawInventory(gp.player,true);
        }
        if(gp.gameState == gp.tradeState){
            drawTradeScreen();
        }
    }
    public void drawDialogueScreen()  {
        //WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = (int) (gp.tileSize * 1.5);
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
    //TRADE
    public void drawTradeScreen(){
        switch (subState){
            case 0: trade_select(); break;
            case 1: trade_buy(); break;
            case 2: trade_sell(); break;
        }
        gp.keyH.enter = false;
    }
    public void trade_select(){
        drawDialogueScreen();
        //DRAW WINDOW

        int x = gp.tileSize * 10;
        int y = gp.tileSize * 2;
        int width =  (int) (gp.tileSize * 4.5);
        int height = (int) (gp.tileSize * 3.5);
        drawSubWindow(x,y,width,height);

        //DRAW TEXT
        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString("Buy seeds", x, y);
        if (commandNum == 0) {
            g2.drawString(">", x-24, y);
            if(gp.keyH.enter){subState = 1;}
        }

        y += gp.tileSize;
        g2.drawString("Sell plants", x, y);
        if (commandNum == 1) {
            g2.drawString(">", x-24, y);
            if(gp.keyH.enter){subState = 2;}
        }
        y += gp.tileSize;
        g2.drawString("Fuck off", x, y);
        if (commandNum == 2) {
            g2.drawString(">", x-24, y);
            if(gp.keyH.enter) {
                commandNum = 0;
                gp.gameState = gp.dialogueState;
                currentDialogue = "Fuck diu, gud pai!";
            }
        }
    }
    public void trade_buy(){
        //Draw player inventory
        drawInventory(gp.player, false);
        //Draw npc inventory
        drawInventory(npc, true);

        //Draw hint window
        int x = gp.tileSize * 5;
        int y = gp.tileSize * 9;
        int width = gp.tileSize * 5;
        int height = gp.tileSize * 2;
        g2.drawString("[ESC] Back", x-130,y+30);

        //Draw player money window
        x = gp.tileSize * 9;
        y = gp.tileSize * 6;
        width = (int) (gp.tileSize * 5.5);
        height = gp.tileSize;
        drawSubWindow(x,y,width, height);
        g2.drawString("Your money: " + gp.player.money, x+8, y+33);

        //Draw price window
        int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow);
        if(itemIndex < npc.inventory.size()){
            x = (int) (gp.tileSize * 5.5);
            y = (int) (gp.tileSize * 5.5);
            width = (int) (gp.tileSize * 2.5);
            height = gp.tileSize;
            drawSubWindow(x,y,width,height);

            int price = npc.inventory.get(itemIndex).price;
            String text = "" + price;
            x = getXforAlignToRightText(text, gp.tileSize * 8 - 28);
            g2.drawString(text,x+10,y+35);

            //Buy an item
            if(gp.keyH.enter){
                if(npc.inventory.get(itemIndex).price > gp.player.money){
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "Hok du tien hai oi!";
                    drawDialogueScreen();
                }
                else if (gp.player.inventory.size() == gp.player.maxInventorySize){
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "Co cho nao nua ma bo nua hai!";
                    //drawDialogueScreen();
                }
                else {
                    gp.player.money -= npc.inventory.get(itemIndex).price;
                    gp.player.inventory.add(npc.inventory.get(itemIndex));
                }
            }
        }

    }
    public void trade_sell(){
        //Draw hint window
        drawInventory(gp.player, true);
        //Draw npc inventory
        drawInventory(npc, false);

        //Draw hint window
        int x = gp.tileSize * 5;
        int y = gp.tileSize * 9;
        int width = gp.tileSize * 5;
        int height = gp.tileSize * 2;
        g2.drawString("[ESC] Back", x-130,y+30);

        //Draw player money window
        x = gp.tileSize * 9;
        y = gp.tileSize * 6;
        width = (int) (gp.tileSize * 5.5);
        height = gp.tileSize;
        drawSubWindow(x,y,width, height);
        g2.drawString("Your money: " + gp.player.money, x+8, y+33);

        //Draw price window
        int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow);
        if(itemIndex < npc.inventory.size()){
            x = (int) (gp.tileSize * 5.5);
            y = (int) (gp.tileSize * 5.5);
            width = (int) (gp.tileSize * 2.5);
            height = gp.tileSize;
            drawSubWindow(x,y,width,height);

            int price = npc.inventory.get(itemIndex).price;
            String text = "" + price;
            x = getXforAlignToRightText(text, gp.tileSize * 8 - 28);
            g2.drawString(text,x+10,y+35);

            //Sell an item
            if (gp.keyH.enter) {
                //sell tools
               //if(gp.player.inventory.get(itemIndex)== gp.player.currentTool){
                   //commandNum = 0;
                   //subState = 0;
                  // gp.gameState = gp.dialogueState;
                  // currentDialogue = "Ban roi lay j lam ma";
               //}
              // else {
                   gp.player.inventory.remove(itemIndex);
                   gp.player.money += price;

             //  }

            }
        }
    }
    public int getItemIndexOnSlot(int slotCol, int slotRow){
        int itemIndex = slotCol + (slotRow*5);
        return  itemIndex;
    }
    public void drawPlayerEnergy() {
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;


        g2.drawImage(energybar0, x-10, y-45, null);

        while (i < gp.player.maxLife) {
            g2.drawImage(energy, x, y, null);
            i++;
            x += gp.tileSize/2.1;
        }
    }
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

    public void drawInventory(Entity entity, boolean cursor){
        int frameX = 0;
        int frameY = 0;
        int frameWidth = 0;
        int frameHeight = 0;
        int slotCol = 0;
        int slotRow = 0;

        if (entity == gp.player){
            frameX = gp.tileSize * 9;
            frameY= gp.tileSize;
            frameWidth = gp.tileSize * 6;
            frameHeight = gp.tileSize * 5;
            slotCol = playerSlotCol;
            slotRow = playerSlotRow;
        } else {
            frameX = gp.tileSize * 2;
            frameY= gp.tileSize;
            frameWidth = gp.tileSize * 6;
            frameHeight = gp.tileSize * 5;
            slotCol = npcSlotCol;
            slotRow = npcSlotRow;
        }
        //FRAME
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
    // SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
    // DRAW PLAYER'S ITEMS
        for(int i=0; i < entity.inventory.size(); i++) {
            g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);
            slotX += gp.tileSize;
            if (i ==4 || i == 9 || i == 14) {
                slotX = slotXstart;
                slotY += gp.tileSize;
            }
        }
    // CURSOR
        if (cursor){
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

            int itemIndex = getItemIndexOnSlot(slotCol, slotRow);

            if(itemIndex < entity.inventory.size()){
                g2.drawString(entity.inventory.get(itemIndex).description, textX, textY);
                textY += 32;
            }
        }
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
    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(164, 76, 69);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        c = new Color(243, 229, 215);
        g2.setColor(c);
        g2.setStroke (new BasicStroke (5));
        g2.drawRoundRect (x+5, y+5, width-10, height-10, 25, 25);
    }

    public int getXforCenteredText ( String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
    public int getXforAlignToRightText (String text, int tailX){
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
}

