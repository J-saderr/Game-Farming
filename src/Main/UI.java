package Main;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import Character.Money;
import Character.Player;
import Environment.EnergyBar;
import Main.Entity.*;
import ItemSystem.*;
import ItemSystem.Entities.Tools.Axe;
import ItemSystem.Entities.Tools.Hoe;
import ItemSystem.Entities.Tools.WateringCan;
import Main.Entity;
import Main.GamePanel;


public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font upheaval, minecraftia;
    public int commandNum = 0;
    BufferedImage energy, energybar0, moneybar0, moneyIcon;
    public String currentDialogue = "";
    int counter = 0;
    public int playerSlotCol =0;
    public int playerSlotRow =0;
    public int npcSlotCol =0;
    public int npcSlotRow =0;
    public int subState = 0;
    public Entity npc;
    public Entity entity = new Entity(gp);

    public UI(GamePanel gp) {
        this.gp =gp;
        //Font chu trong game
        try {
            InputStream is = getClass().getResourceAsStream("/font/Minecraftia-Regular.ttf");
            minecraftia = Font.createFont(Font.TRUETYPE_FONT, is);
            is = getClass().getResourceAsStream("/font/upheavtt.ttf");
            upheaval = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Entity money = new Money(gp);
        Entity energybar = new EnergyBar(gp);
        energy = energybar.enbar;
        energybar0 = energybar.enbar0;
        moneybar0 = money.monbar;
        moneyIcon = money.monicon;

    }
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        g2.setFont(minecraftia);
        g2.setColor(Color.white);
        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }
        if (gp.gameState == gp.playerState) {
            drawPlayerEnergy();
            drawPlayerMoney();
        }
        if (gp.gameState == gp.pauseState) {
            drawPlayerEnergy();
            drawPlayerMoney();
            drawPauseScreen();
        }
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }
        if (gp.gameState == gp.characterState) {
            drawPlayerEnergy();
            drawPlayerMoney();
            drawCharacterScreen();
            drawInventory(gp.player,true);
        }
        if (gp.gameState == gp.sleepState) {
            drawSleepScreen();
            drawPlayerEnergy();
            drawPlayerMoney();
        }
        if (gp.gameState == gp.houseState) {
            drawPlayerEnergy();
            drawPlayerMoney();
            drawSleepOrUpdate();
            if (!gp.keyH.canSleep) {drawCannotSleep();}
        }
        if (gp.gameState == gp.houselvState) {
            drawPlayerEnergy();
            drawPlayerMoney();
            drawYourHouseLevel();
        }
        if (gp.gameState == gp.cannotUpdateState) {
            drawPlayerEnergy();
            drawPlayerMoney();
            drawYourHouseLevel();
            drawCannotUpdate();
        }
        if(gp.gameState == gp.tradeState){
            drawTradeScreen();
        }
        if(gp.gameState == gp.gameOverState){
            drawGameOverScreen();
        }
    }

    private void drawGameOverScreen() {
        g2.setColor(new Color(0,0,1,50));
        g2.fillRect(0,0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
        text = "Game Over";
        //shadow
        g2.setColor(Color.BLACK);
        x = getXforCenteredText(text);
        y = gp.tileSize*4;
        g2.drawString(text, x, y);
        //main
        g2.setColor(Color.WHITE);
        g2.drawString(text, x-4, y-4);

        //retry
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
        text = "Retry";
        x = getXforCenteredText(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);

        if (commandNum == 0) {
            g2.drawString(">", x - 40, y);
        }

        //back to title screen
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
        text = "Quit";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);

        if (commandNum == 1) {
            g2.drawString(">", x - 40, y);
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
        int x = gp.tileSize * 8;
        int y = gp.tileSize * 3;
        int width =  (int) (gp.tileSize * 6);
        int height = (int) (gp.tileSize * 3.5);
        drawSubWindow(x,y,width,height);

        //DRAW TEXT
        //Set font
        //Color c = new Color(102, 55, 68);
        g2.setColor(Color.WHITE);
        g2.setFont(minecraftia);
        g2.setFont(g2.getFont().deriveFont(17f));

        x += gp.tileSize + 10;
        y += gp.tileSize + 5;

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
        g2.drawString("Back to farm", x, y);
        if (commandNum == 2) {
            g2.drawString(">", x-24, y);
            if(gp.keyH.enter) {
                commandNum = 0;
                gp.gameState = gp.dialogueState;
                currentDialogue = "Whenever you need, \n I'm always here for you!";
            }
        }
    }
    public void trade_buy(){
        //Set font
        //Color c = new Color(102, 55, 68);
        g2.setColor(Color.WHITE);
        g2.setFont(minecraftia);
        g2.setFont(g2.getFont().deriveFont(25f));

        //Draw player inventory
        drawInventory(gp.player, false);
        //Draw npc inventory
        drawInventory(npc, true);

        //Draw hint window
        int x = gp.tileSize * 5;
        int y = gp.tileSize * 9;
        int width = gp.tileSize * 3;
        int height = gp.tileSize * 2;
        g2.drawString("[ESC] Back", x-120,y-155);

        //Draw player money window
        drawPlayerMoney();

        //Draw price window
        int itemIndex = getItemIndexOnSlot(npcSlotCol, npcSlotRow);
        if(itemIndex < npc.inventory.size()){
            x = (int) (gp.tileSize * 5.5);
            y = (int) (gp.tileSize * 5.5);
            width = (int) (gp.tileSize * 2.5);
            height = gp.tileSize;
            drawSubWindow(x,y,width,height);
            g2.drawImage(moneyIcon,x+90, y+13,23,23,null);

            int price = npc.inventory.get(itemIndex).price;
            String text = "" + price;
            x = getXforAlignToRightText(text, gp.tileSize * 8 - 28);
            g2.drawString(text,x-2,y+33);

            //Buy an item
            if(gp.keyH.enter){
                if(npc.inventory.get(itemIndex).price > gp.player.money){
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "You don't have enough money!";
                    drawDialogueScreen();
                } else {
                    if (gp.player.canObtainItem(npc.inventory.get(itemIndex))) {
                        gp.player.money -= npc.inventory.get(itemIndex).price;
                    } else {
                        subState = 0;
                        gp.gameState = gp.dialogueState;
                        currentDialogue = "You can't carry anymore!";
                    }
                }
            }
        }
    }
    public void trade_sell(){
        g2.setFont(minecraftia);
        g2.setFont(g2.getFont().deriveFont(25f));
        //Draw player inventory
        drawInventory(gp.player, true);
        //Draw npc inventory
        drawInventory(npc, false);
        //Draw hint window
        int x = gp.tileSize * 5;
        int y = gp.tileSize * 9;
        int width = gp.tileSize * 5;
        int height = gp.tileSize;
        g2.drawString("[ESC] Back", x-120,y-155);

        //Draw player money window
        drawPlayerMoney();

        //Draw price window
        int itemIndex = getItemIndexOnSlot(playerSlotCol, playerSlotRow);
        if(itemIndex < gp.player.inventory.size()){
            x = (int) (gp.tileSize * 5.5);
            y = (int) (gp.tileSize * 5.5);
            width = (int) (gp.tileSize * 2.5);
            height = gp.tileSize;
            drawSubWindow(x,y,width,height);
            g2.drawImage(moneyIcon,x+90, y+13,23,23,null);
            int price = gp.player.inventory.get(itemIndex).price;
            String text = "" + price;
            x = getXforAlignToRightText(text, gp.tileSize * 8 - 28);
            g2.drawString(text,x-2,y+33);

            //Sell an item
            if (gp.keyH.enter) {
                //sell tools
                if(gp.player.inventory.get(itemIndex).type == Entity.type_hoe ||
                        gp.player.inventory.get(itemIndex).type == Entity.type_axe ||
                        gp.player.inventory.get(itemIndex).type == Entity.type_watercan){
                    commandNum = 0;
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "You can't sell tool!";
                } else {
                    if (gp.player.inventory.get(itemIndex).quantities > 1) {
                        gp.player.inventory.get(itemIndex).quantities--;
                    }
                    else {
                        gp.player.inventory.remove(itemIndex);
                    }
                    gp.player.money += price;
                }
            }
        }
    }
    public void drawInventory(Entity entity, boolean cursor) {
        int frameX = 0;
        int frameY = 0;
        int frameWidth = 0;
        int frameHeight = 0;
        int slotCol = 0;
        int slotRow = 0;

        if (entity == gp.player){
            frameX = gp.tileSize * 9;
            frameY = gp.tileSize;
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
        int slotSize = gp.tileSize + 3;

        // DRAW ITEMS
        for (int i = 0; i < entity.inventory.size(); i++) {
            //EQUIP CURSOR
            if (entity.inventory == gp.player.inventory && gp.player.inventory.get(i) == gp.player.currentTool){
                g2.setColor(Color.YELLOW);
                g2.fillRoundRect(slotX,slotY,gp.tileSize,gp.tileSize,10,10);
            }
            g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);

            //DISPLAY AMOUNT
            if (entity.inventory.get(i).quantities > 1){
                g2.setFont(g2.getFont().deriveFont(20));
                int amountX;
                int amountY;

                String s = "" + entity.inventory.get(i).quantities;
                amountX = getXforAlignToRightText(s, slotX + 44);
                amountY = slotY + gp.tileSize;

                //SHADOW
                g2.setColor(new Color(60,60,60));
                g2.drawString(s, amountX, amountY);

                //NUMBER
                g2.setColor(Color.WHITE);
                g2.drawString(s, amountX-3, amountY-3);
            }
            slotX += slotSize;
            if (i ==4 || i == 9 || i == 14) {
                slotX = slotXstart;
                slotY += slotSize;
            }
        }
        // CURSOR
        if (cursor) {
            int cursorX = slotXstart + (slotSize * slotCol);
            int cursorY = slotYstart + (slotSize * slotRow);
            int cursorWidth = gp.tileSize;
            int cursorHeight = gp.tileSize;

            // DRAW CURSOR
            Color c = new Color(243, 229, 215);
            g2.setColor(c);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

            //Description Frame
            int dFrameX = frameX;
            int dFrameY = frameY + frameHeight;
            int dFrameWidth = frameWidth;
            int dFrameHeight = gp.tileSize*3;
            //drawSubWindow(dFrameX,dFrameY, dFrameWidth, dFrameHeight);

            //Draw description text
            int textX = dFrameX + 20;
            int textY = dFrameY + gp.tileSize;
            g2.setColor(Color.WHITE);
            g2.setFont(minecraftia);
            g2.setFont(g2.getFont().deriveFont(15f));

            int itemIndex = getItemIndexOnSlot(slotCol,slotRow);

            if(itemIndex < entity.inventory.size()){
                drawSubWindow(dFrameX,dFrameY, dFrameWidth, dFrameHeight);
                for (String line: entity.inventory.get(itemIndex).description.split("\n")){
                    g2.drawString(line, textX, textY);
                    textY += 32;
                }
            }
        }
    }
    public int getItemIndexOnSlot(int slotCol, int slotRow){
        int itemIndex = slotCol + (slotRow*5);
        return  itemIndex;
    }


    //title-screen
    public void drawTitleScreen() {
        int nup = 0;
        int nleft = 0;
        int nright = 0;
        int ndown = 0;
        while (nup < 15) {
            g2.drawImage(gp.tileManager.tile[2].image, gp.tileSize * nup, -10, gp.tileSize * 3, gp.tileSize * 3, null);
            nup = nup +3;
        }
        while (nleft < 15) {
            g2.drawImage(gp.tileManager.tile[4].image, -10, gp.tileSize * nleft, gp.tileSize * 3, gp.tileSize * 3, null);
            nleft = nleft +3;
        }
        while (nright < 15) {
            g2.drawImage(gp.tileManager.tile[8].image, gp.tileSize * 12 + 50, gp.tileSize * nright, gp.tileSize * 3, gp.tileSize * 3, null);
            nright = nright +3;
        }
        while (ndown < 15) {
            g2.drawImage(gp.tileManager.tile[6].image, gp.tileSize * ndown, gp.tileSize * 9, gp.tileSize * 3, gp.tileSize * 3, null);
            ndown = ndown +3;
        }
        g2.drawImage(gp.tileManager.tile[3].image, -10, 0, gp.tileSize * 3, gp.tileSize * 3, null);
        g2.drawImage(gp.tileManager.tile[9].image, gp.tileSize * 12 + 50, 0, gp.tileSize * 3, gp.tileSize * 3, null);
        g2.drawImage(gp.tileManager.tile[5].image, -10, gp.tileSize * 9, gp.tileSize * 3, gp.tileSize * 3, null);
        g2.drawImage(gp.tileManager.tile[7].image, gp.tileSize * 12 + 50, gp.tileSize * 9, gp.tileSize * 3, gp.tileSize * 3, null);
        // title-name
        g2.setFont(upheaval);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,65F));
        String text = "BetterFarming";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 3 + 30;
        // shadow-color
//        Color c = new Color(164, 76, 69);
        Color d = new Color(55, 66, 44);
        g2.setColor(d);
        g2.drawString(text, x+4, y+4);
        // main-color
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);
        // character-image
        x = gp.screenHeight/2 - (gp.tileSize*2)/2;
        y += gp.tileSize*2;
        g2.drawImage(gp.player.down1, x+20, y-70, gp.tileSize*3, gp.tileSize*3, null);
        g2.drawImage(gp.player.up2, x+130, y-70, gp.tileSize*3, gp.tileSize*3, null);
        g2.drawImage(gp.tileManager.tile[16].image, gp.tileSize * 12, gp.tileSize * 7, gp.tileSize * 2, gp.tileSize * 2, null);
        g2.drawImage(gp.tileManager.tile[16].image, gp.tileSize * 10, gp.tileSize * 7, gp.tileSize * 2, gp.tileSize * 2, null);
        g2.drawImage(gp.tileManager.tile[16].image, gp.tileSize * 11, gp.tileSize * 8, gp.tileSize * 2, gp.tileSize * 2, null);
        g2.drawImage(gp.tileManager.tile[19].image, x-120, gp.tileSize * 8 + 20, gp.tileSize * 2, gp.tileSize * 2, null);
        g2.drawImage(gp.houselv.house4, x-190, y-90, gp.tileSize*5, gp.tileSize*5, null);
        //g2.drawImage(gp.houselv.house5, x+255, y+30, gp.tileSize*4, gp.tileSize*4, null);


        // menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,40F));
        g2.setColor(Color.WHITE);
        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize*3;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "QUIT GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
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
        g2.setFont(minecraftia);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,15F));
        //Description Frame
        int dFrameX = gp.tileSize - 30 ;
        int dFrameY = gp.tileSize + 20;
        int dFrameWidth = gp.tileSize *6;
        int dFrameHeight = gp.tileSize*3;
        drawSubWindow(dFrameX,dFrameY, dFrameWidth, dFrameHeight);

        //Draw description text
        int textX = dFrameX + 20;
        int textY = dFrameY + 50;
        g2.setFont(g2.getFont().deriveFont(15F));
        g2.drawString("Choose an action:", textX, textY);
        g2.drawString("1. Sleep (press Enter)", textX, textY+25);
        g2.drawString("2. Update House (press U)", textX, textY+50);
    }
    private void drawSleepScreen() {
        counter++;
        if (counter < 120) {
            gp.eManager.lighting.filterAlpha += 0.1f;
            if (gp.eManager.lighting.filterAlpha > 1f) {
                gp.eManager.lighting.filterAlpha = 1f;
            }
        }
        if (counter >= 120) {
            gp.eManager.lighting.filterAlpha -= 0.1f;
            if (gp.eManager.lighting.filterAlpha <= 0f) {
                gp.eManager.lighting.filterAlpha = 0f;
                gp.eManager.lighting.dayCounter = 0;
                counter = 0;
                gp.eManager.lighting.dayState = gp.eManager.lighting.day;
                gp.gameState = gp.playerState;
            }
        }
    }
    public void drawCannotSleep() {
        g2.setFont(minecraftia);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,15F));
        //Description Frame
        int dFrameX = gp.tileSize - 30 ;
        int dFrameY = gp.tileSize + 180;
        int dFrameWidth = gp.tileSize *6;
        int dFrameHeight = gp.tileSize*2;
        drawSubWindow(dFrameX,dFrameY, dFrameWidth, dFrameHeight);

        //Draw description text
        int textX = dFrameX + 20;
        int textY = dFrameY + 50;
        g2.setFont(g2.getFont().deriveFont(15F));
        g2.drawString("Cannot sleep at day!", textX, textY);
        g2.drawString("Press O to Exit", textX, textY+ 25);
    }

    public void drawCannotUpdate() {
        g2.setFont(minecraftia);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,15F));
        //Description Frame
        int dFrameX = gp.tileSize - 30;
        int dFrameY = gp.tileSize + 200;
        int dFrameWidth = gp.tileSize * 6;
        int dFrameHeight = gp.tileSize*2;
        drawSubWindow(dFrameX,dFrameY, dFrameWidth, dFrameHeight);

        //Draw description text
        int textX = dFrameX + 20;
        int textY = dFrameY + 50;
        g2.setFont(g2.getFont().deriveFont(15F));
        g2.drawString("Not enough money", textX, textY);
        g2.drawString("Press O to Exit", textX, textY+ 25);

    }

    public void drawYourHouseLevel() {
        g2.setFont(minecraftia);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,15F));
        //Description Frame
        int dFrameX = gp.tileSize - 30 ;
        int dFrameY = gp.tileSize + 30;
        int dFrameWidth = gp.tileSize *6 + 60;
        int dFrameHeight = gp.tileSize*3 + 20;
        drawSubWindow(dFrameX,dFrameY, dFrameWidth, dFrameHeight);

        //Draw description text
        int textX = dFrameX + 20;
        int textY = dFrameY + 50;
        g2.setFont(g2.getFont().deriveFont(15F));
        if (gp.houselv.houseLevel < 6) {
            g2.drawString("Your House Level is " + gp.houselv.houseLevel, textX, textY);
            g2.drawString("You need " + gp.keyH.levelUpMoney + " to update your", textX, textY + 25);
            g2.drawString("House Level.", textX, textY + 50);
            g2.drawString("Press Enter to Update", textX, textY + 75);
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


        g2.drawImage(energybar0, x-8, y-53, null);

        while (i < gp.player.life) {
            g2.drawImage(energy, x, y, null);
            i++;
            x += gp.tileSize/2.1;
        }
    }
    // money bar
    public void drawPlayerMoney() {
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        g2.drawImage(moneybar0, x*26, y-45, null);
        Color c = new Color(102, 55, 68);
        g2.setColor(c);
        g2.setFont(upheaval);
        g2.setFont(g2.getFont().deriveFont(30F));
        g2.drawString("" + gp.player.money, 668, 43);
        Color d = new Color(164, 76, 69);
        g2.setColor(d);
        g2.drawString("" + gp.player.money, 670, 45);
    }
    // dialogues
    public void drawDialogueScreen()  {
        //WINDOW
        int x = gp.tileSize * 2;
        int y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = (int) (gp.tileSize * 2.5);
        drawSubWindow(x, y, width, height);

        //Set font
        //Color c = new Color(102, 55, 68);
        g2.setColor(Color.WHITE);
        g2.setFont(minecraftia);
        g2.setFont(g2.getFont().deriveFont(17f));

        x += gp.tileSize;
        y += gp.tileSize + 7;
        //DISPLAY MULTIPLES LINES
        for(String line: currentDialogue.split("\n")) {
            g2.drawString(line,x,y);
            y += 40;
        }
    }

    // inventory

    public void drawCharacterScreen(){
        int frameX = gp.tileSize * 9;
        int frameY= gp.tileSize;
        int frameWidth = gp.tileSize * 6;
        int frameHeight = gp.tileSize * 5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);
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
    public int getXforAlignToRightText (String text, int tailX){
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
    public int getXforCenteredText ( String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}


