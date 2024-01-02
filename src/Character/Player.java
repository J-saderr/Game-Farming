package Character;

import ItemSystem.Entities.Seed.Carrot;
import ItemSystem.Entities.Seed.Potato;
import ItemSystem.Entities.Seed.Spinach;
import ItemSystem.Entities.Tools.Axe;
import ItemSystem.Entities.Tools.Hoe;
import ItemSystem.Entities.Tools.WateringCan;
import Main.*;
import Object.*;
import HouseLevel.*;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Player extends Entity {
        public GamePanel gp;
        KeyHandler keyH;
        private BufferedImage upload;
        public final int screenX;
        public final int screenY;
        int hasSoil = 0;
        public ArrayList<Entity> inventory = new ArrayList<>();
        public final int maxInventorySize = 20;

        public Player(GamePanel gp, KeyHandler keyH) {
            super(gp);
            this.keyH = keyH;
            screenX = gp.screenWidth/2 - (gp.tileSize/2);
            screenY = gp.screenHeight/2 - (gp.tileSize/2);
            solidArea = new Rectangle(8, 16, 32, 32);
            solidAreaDefaultX = solidArea.x;
            solidAreaDefaultY = solidArea.y;
            setDefault();
            getPlayerImage();
            setItems();
            doingWorkImage();
        }
        public void setDefault() {
            worldX = super.gp.tileSize * 5;
            worldY = super.gp.tileSize * 5;
            speed = 4;
            direction = "down";
            currentTool = new WateringCan(gp);
            //player-status
            maxLife = 5;
            life = 3;
        }
        public void setItems(){
            inventory.add(new WateringCan(gp));
            inventory.add(new Hoe(gp));
            inventory.add(new Axe(gp));
            inventory.add(new Carrot(gp));
            inventory.add(new Potato(gp));
            inventory.add(new Spinach(gp));
        }
        public void getPlayerImage() {
            try
                (InputStream inputStream00 = new FileInputStream(new File("res/player/left1.png"));
                 InputStream inputStream01 = new FileInputStream(new File("res/player/left2.png"));
                 InputStream inputStream02 = new FileInputStream(new File("res/player/left3.png"));
                 InputStream inputStream03 = new FileInputStream(new File("res/player/right1.png"));
                 InputStream inputStream04 = new FileInputStream(new File("res/player/right2.png"));
                 InputStream inputStream05 = new FileInputStream(new File("res/player/right3.png"));
                 InputStream inputStream06 = new FileInputStream(new File("res/player/back1.png"));
                 InputStream inputStream07 = new FileInputStream(new File("res/player/back2.png"));
                 InputStream inputStream08 = new FileInputStream(new File("res/player/back3.png"));
                 InputStream inputStream09 = new FileInputStream(new File("res/player/up1.png"));
                 InputStream inputStream10 = new FileInputStream(new File("res/player/up2.png"));
                 InputStream inputStream11 = new FileInputStream(new File("res/player/up3.png"))) {
                left1 = ImageIO.read(inputStream00);
                left2 = ImageIO.read(inputStream01);
                left3 = ImageIO.read(inputStream02);
                right1 = ImageIO.read(inputStream03);
                right2 = ImageIO.read(inputStream04);
                right3 = ImageIO.read(inputStream05);
                up1 = ImageIO.read(inputStream06);
                up2 = ImageIO.read(inputStream07);
                up3 = ImageIO.read(inputStream08);
                down1 = ImageIO.read(inputStream09);
                down2 = ImageIO.read(inputStream10);
                down3 = ImageIO.read(inputStream11);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        public void update() {
            if(doing == true){
                doing();
            }
            else if (keyH.up || keyH.down || keyH.right || keyH.left || keyH.enter) {
                if (keyH.up) {
                    direction = "up";
                } else if (keyH.down) {
                    direction = "down";
                } else if(keyH.left) {
                    direction = "left";
                } else if (keyH.right) {
                    direction = "right";
                }

                //Check Tile Collision
                collisionOn = false;
                super.gp.collision.checkTile(this);

                //Check Object Collision
                int objIndex = super.gp.collision.checkObject(this, true);
                changeSoil(objIndex);
                int npcIndex = super.gp.collision.checkEntity(this, super.gp.npc);
                interactNPC(npcIndex);
                int houseIndex = super.gp.collision.checkHouse(this, super.gp.house);
                interactHouse(houseIndex);

                //Check Sleep
                super.gp.sleeping.checkSleep();

//                // check-energy-recover
//                if(super.gp.keyH.isSleeping) {
//                    System.out.println("t met qua ne");
//                    life = maxLife;
//                }
                //If Collision is False, player can move
                if (collisionOn == false && keyH.enter == false) {
                    switch(direction) {
                        case "up": worldY -= speed; break;
                        case "down": worldY += speed; break;
                        case "left": worldX -= speed; break;
                        case "right": worldX += speed; break;
                    }
                }
                super.gp.keyH.enter = false;

            spriteCounter++;
            if(spriteCounter > 12) {
                if(spriteNum == 1) {
                    spriteNum = 2;
                }
                else if(spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }
    public void doingWorkImage(){
        if(currentTool.type== type_watercan){
            doRight1 = setuptool("res/Action/Watercan/water1",super.gp.tileSize,super.gp.tileSize);
            doRight2 = setuptool("res/Action/Watercan/water2",super.gp.tileSize,super.gp.tileSize);
        }
        if(currentTool.type== type_hoe){
            doRight1 = setuptool("res/Action/Hoe/hoe1",super.gp.tileSize,super.gp.tileSize);
            doRight2 = setuptool("res/Action/Hoe/hoe2",super.gp.tileSize,super.gp.tileSize);
        }
        if(currentTool.type== type_axe){
            doRight1 = setuptool("res/Action/Axe/axe1",super.gp.tileSize,super.gp.tileSize);
            doRight2 = setuptool("res/Action/Axe/axe2",super.gp.tileSize,super.gp.tileSize);
        }
    }
    public void doing () {

        spriteCounter++;
        if (spriteCounter <= 5) {
            spriteNum=1;
        }
        if(spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;
        }
        if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            doing = false;
        }
    }
    public void selectItem(){
        int itemIndex = super.gp.ui.getItemIndexOnSlot();
        if(itemIndex<inventory.size()){
            Entity selectedItem = inventory.get(itemIndex);
            if(selectedItem.type == type_watercan ||selectedItem.type == type_axe || selectedItem.type == type_hoe){
                currentTool = selectedItem;
                doingWorkImage();
            }
        }
    }
    public void interactNPC(int i){
        if(super.gp.keyH.enter == true){
            if(i != 999) {
                super.gp.gameState = super.gp.dialogueState;
                super.gp.npc[i].speak();
            }
            else {
                doing = true;
            }
        }
    }

    public void interactHouse(int i) {
            if (i != 999) {
                System.out.println("Hitting House");
            }
    }

    public void changeSoil(int i) {
        if (i != 999) {
            String objectName = super.gp.obj[i].name;

            switch (objectName) {
                case "Soil":
                    hasSoil++;
                    super.gp.obj[i] = new notWateredSoil(gp);
                    break;
            }
        }
    }
        public void draw (Graphics2D g2) {
            //g2.setColor(Color.black);
            //g2.dispose();

            BufferedImage image = null;
            if (direction != null) {
                switch (direction) {
                    case "left":
//                        if(doing == false){
                            if(spriteNum == 1) {
                                image = left1;
                            }
                            if(spriteNum == 2) {
                                image = left2;
                            }
//                        }
//                        if(doing == true){
//                            if(spriteNum == 1) {
//                                image = doLeft1;
//                            }
//                            if(spriteNum == 2) {
//                                image = doLeft2;
//                            }
//                        }
                        break;
                    case "right":
                        if(doing == false){
                            if(spriteNum == 1) {
                                image = right1;
                            }
                            if(spriteNum == 2) {
                                image = right2;
                            }
                        }
                        if(doing == true){
                            if(spriteNum == 1) {
                                image = doRight1;
                            }
                            if(spriteNum == 2) {
                                image = doRight2;
                            }
                        }
                        break;
                    case "up":
//                        if(doing == false){
                            if(spriteNum == 1) {
                                image = up1;
                            }
                            if(spriteNum == 2) {
                                image = up2;
                            }
//                        }
//                        if(doing == true){
//                            if(spriteNum == 1) {
//                                image = doUp1;
//                            }
//                            if(spriteNum == 2) {
//                                image = doUp2;
//                            }
//                        }
                        break;
                    case "down":
//                        if(doing == false){
                            if(spriteNum == 1) {
                                image = down1;
                            }
                            if(spriteNum == 2) {
                                image = down2;
                            }
//                        }
//                        if(doing == true){
//                            if(spriteNum == 1) {
//                                image = doDown1;
//                            }
//                            if(spriteNum == 2) {
//                                image = doDown2;
//                            }
//                        }
                        break;
                }
            }
            if (image != null) {
                g2.drawImage(image, screenX, screenY, super.gp.tileSize, super.gp.tileSize, null);
            }
        }
}
