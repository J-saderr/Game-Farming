package Character;

import ItemSystem.Entities.Tools.Axe;
import ItemSystem.Entities.Tools.Hoe;
import ItemSystem.Entities.Tools.WateringCan;
import Main.*;
import ItemSystem.Entities.Soil.notWateredSoil;
import ItemSystem.Entities.Soil.wateredSoil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import ItemSystem.Entities.Crop.*;
import Main.Entity;

public class Player extends Entity {
    KeyHandler keyH;

    private Carrot carrot = new Carrot(gp);
    private CarrotMature carrotMature = new CarrotMature(gp);
    private Potato potato = new Potato(gp);
    private Spinach spinach = new Spinach(gp);
    public final int screenX ;
    public final int screenY ;
    public final int maxInventorySize = 20;


    public Entity currentTool;
    public boolean isWater = false;
    public boolean soilWater = false;
    wateredSoil wateredsoil = new wateredSoil(gp);
    notWateredSoil notWateredSoil = new notWateredSoil(gp);

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
            //selectItem();
        }
        public void setDefault() {
            worldX = super.gp.tileSize * 5;
            worldY = super.gp.tileSize * 5;
            speed = 4;
            direction = "down";
            currentTool = new Hoe(gp);
            //player-status
            maxLife = 11;
            life = maxLife;
            money = 500;
        }
        public void setItems(){
            inventory.add(new WateringCan(gp));
            inventory.add(new Hoe(gp));
            inventory.add(new Axe(gp));
            inventory.add(new Carrot(gp));
            inventory.add(new Potato(gp));
            inventory.add(new Spinach(gp));
            inventory.add(new CarrotMature(gp));
            inventory.add(new PotatoMature(gp));
            inventory.add(new SpinachMature(gp));
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
            if(doing){
                doing();
            }
            else if (keyH.up || keyH.down || keyH.right || keyH.left || keyH.enter||keyH.doing||keyH.harvest) {
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
                interactSoil(npcIndex);
                int houseIndex = super.gp.collision.checkHouse(this, super.gp.house);
                interactHouse(houseIndex);
                harvestCrop(objIndex);

                //Check Sleep
                super.gp.sleeping.checkSleep();
                //Check Event
                super.gp.eHandler.checkEvent();

                //If Collision is False, player can move
                if (collisionOn == false && keyH.enter == false && keyH.doing ==false && keyH.harvest ==false) {
                    switch(direction) {
                        case "up": worldY -= speed; break;
                        case "down": worldY += speed; break;
                        case "left": worldX -= speed; break;
                        case "right": worldX += speed; break;
                    }
                }
                super.gp.keyH.enter = false;
                super.gp.keyH.doing =false;
                super.gp.keyH.harvest=false;

                spriteCounter++;
                if (spriteCounter > 12) {
                    if (spriteNum == 1) {
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;
                }
            }
            isWater = false;
            Watering();
            WateringSoil();
            plantCrop();

            if (life > maxLife) {
                life = maxLife;
            }
            if (life == 0) {
                gp.gameState = gp.gameOverState;
            }
        }
    public void doingWorkImage(){
        if(currentTool.type== type_hoe){
            doRight1 = setuptool("res/Action/Hoe/hoe1",super.gp.tileSize,super.gp.tileSize);
            doRight2 = setuptool("res/Action/Hoe/hoe2",super.gp.tileSize,super.gp.tileSize);
        }
        if(currentTool.type== type_watercan){
            doRight1 = setuptool("res/Action/Watercan/water1",super.gp.tileSize,super.gp.tileSize);
            doRight2 = setuptool("res/Action/Watercan/water2",super.gp.tileSize,super.gp.tileSize);
        }
        if(currentTool.type== type_axe){
            doRight1 = setuptool("res/Action/Axe/axe1",super.gp.tileSize,super.gp.tileSize);
            doRight2 = setuptool("res/Action/Axe/axe2",super.gp.tileSize,super.gp.tileSize);
        }

    }
  
    public void doing() {

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
        int itemIndex = super.gp.ui.getItemIndexOnSlot(super.gp.ui.playerSlotCol, super.gp.ui.playerSlotRow);
        if(itemIndex<inventory.size()){
            Entity selectedItem = inventory.get(itemIndex);
            if(selectedItem.type == type_watercan ||selectedItem.type == type_axe || selectedItem.type == type_hoe || selectedItem.type == type_carrot || selectedItem.type == type_potato || selectedItem.type == type_spinach){
                currentTool = selectedItem;
                doingWorkImage();
            }
        }
    }
    public void interactSoil(int i){
        if(super.gp.keyH.doing){
                doing = true;
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
                    if (keyH.doing & currentTool.type == type_hoe){

                        gp.obj[i].image = notWateredSoil.image;
                        gp.obj[i].name = "notWateredSoil";
                    }
                    break;
            }
        }
    }
    public void Watering(){
        // check button and tool
        if (keyH.doing & currentTool.type== type_watercan){
            isWater = true;
        }
    }

    public void WateringSoil() {
        if (isWater) {
            int objIndex = super.gp.collision.checkObject(this, true);
            if (objIndex != 999) {

                String objectName = gp.obj[objIndex].name;

                switch (objectName) {
                    case "notWateredSoil":
                        gp.obj[objIndex].image = wateredsoil.image;
                        gp.obj[objIndex].name = "wateredSoil";
                        break;
                    default:
                        System.out.println("exception");
                        break;
                }
            }
        }
    }

    //check coordinate
    public int getSoilX(int i) {
        if (i != 999) {
            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "notWateredSoil","wateredSoil":
                    return (gp.obj[i].worldX);
                default:
                    System.out.println("exception");
                    break;
            }

        }
        return 0;
    }
    public int getSoilY(int i) {
        if (i != 999) {
            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "notWateredSoil", "wateredSoil":
                    return (gp.obj[i].worldY);
                default:
                    System.out.println("exception");
                    break;
            }

        }
        return 0;
    }
    public void plantCrop() {

        //Check Object Collision
        int objIndex = super.gp.collision.checkObject(this, true);
        //plant

        if (currentTool.type == type_carrot & keyH.doing & objIndex != 999 & getSoilX(objIndex)!=0) {
            for (int i = 0; i <= 23; i++) {
                if (gp.obj[i].name != "Soil" && gp.entities[i] == null && gp.obj[i].worldX == getSoilX(objIndex) && gp.obj[i].worldY == getSoilY(objIndex)){
                    for (Entity item: inventory) {
                        if (item.quantities >0 && item.type == type_carrot) {
                            gp.entities[i] = new Carrot(gp);
                            gp.entities[i].worldX = getSoilX(objIndex);
                            gp.entities[i].worldY = getSoilY(objIndex);
                            gp.entities[i].image = carrot.Carrot_seed;
                            item.quantities -= 1;
                        }
                        else {System.out.println("Not enough seed");}

                    };


                }
            }

        }
        if (currentTool.type == type_potato & keyH.doing & objIndex != 999 & getSoilX(objIndex)!=0) {
            for (int i = 0; i <= 23; i++) {
                if (gp.obj[i].name != "Soil" && gp.entities[i] == null && gp.obj[i].worldX == getSoilX(objIndex) && gp.obj[i].worldY == getSoilY(objIndex)){
                    for (Entity e: inventory) {
                        if (e.quantities >0 && e.type == type_potato ) {
                            gp.entities[i] = new Potato(gp);
                            gp.entities[i].worldX = getSoilX(objIndex);
                            gp.entities[i].worldY = getSoilY(objIndex);
                            gp.entities[i].image = potato.Potato_seed;
                            e.quantities -= 1;
                            e.description = "Potato seed x " + e.quantities;
                        }
                        else {System.out.println("Not enough seed");}
                    };


                }
            }

        }
        if (currentTool.type == type_spinach & keyH.doing & objIndex != 999 & getSoilX(objIndex)!=0) {
            for (int i = 0; i <= 23; i++) {
                if (gp.obj[i].name != "Soil" && gp.entities[i] == null && gp.obj[i].worldX == getSoilX(objIndex) && gp.obj[i].worldY == getSoilY(objIndex)) {
                    for (Entity e : inventory) {
                        if (e.quantities > 0 && e.type == type_spinach) {
                            gp.entities[i] = new Spinach(gp);
                            gp.entities[i].worldX = getSoilX(objIndex);
                            gp.entities[i].worldY = getSoilY(objIndex);
                            gp.entities[i].image = spinach.Spinach_seed;
                            e.quantities -= 1;
                            e.description = "Spinach seed x " + e.quantities;
                        } else {
                            System.out.println("Not enough seed");
                        }
                    }
                }
            }
        }

    }
    public void checkWatering(int i) {
        if(gp.obj[i].name == "wateredSoil" && gp.entities[i] != null) {
            count[i] += 1;
            if (count[i] == 1){
                gp.entities[i].waterDay[i] += 1;}

        }
        count[i] = 0;
    }
    public void harvestCrop(int i) {
        //Check Object Collision
        if (i != 999 ){
            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "notWateredSoil", "wateredSoil":
                    if (gp.entities[i] != null){
                        if (gp.entities[i].cropPeriod == 3 && keyH.harvest) {
                            if (gp.entities[i].type == type_carrot) {
                                for (Entity e: inventory) {
                                    if (e.type == type_carrot_mature) {
                                        e.quantities += 1;
                                        e.description = "Carrot x " + e.quantities;
                                    }
                                }
                            }
                            if (gp.entities[i].type == type_potato) {
                                for (Entity e: inventory) {
                                    if (e.type == type_potato_mature) {
                                        e.quantities += 1;
                                        e.description = "Potato x " + e.quantities;
                                    }
                                }
                            }
                            if (gp.entities[i].type == type_spinach) {
                                for (Entity e : inventory) {
                                    if (e.type == type_spinach_mature) {
                                        e.quantities += 1;
                                        e.description = "Spinach x " + e.quantities;
                                    }
                                }
                            }
                            gp.obj[i].name = "notWateredSoil";
                            gp.obj[i].image = notWateredSoil.image;
                            gp.entities[i].waterDay[i] = 0;
                            gp.entities[i].image = null;
                            gp.entities[i] = null;

                        }
                    }

                    break;
            }
        }
    }
    public int searchItemInventory(String itemName){
        int itemIndex = 999;

        for (int i = 0; i < inventory.size(); i++){
            if (inventory.get(i).name.equals((itemName))){
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
    }
    public boolean canObtainItem(Entity item) {
        boolean canObtain = false;
        //check if stackable
        if (item.stackable) {
            int index = searchItemInventory(item.name);
            if (index != 999) {
                inventory.get(index).quantities++;
                canObtain = true;
            } else { //check xem inventory con cho trong khong
                if (inventory.size() != maxInventorySize) {
                    inventory.add(item);
                    canObtain = true;
                }
            }
        } else { //not stackable, check xem con cho trong khong
            if (inventory.size() != maxInventorySize) {
                inventory.add(item);
                canObtain = true;
            }
        }
        return canObtain;
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
