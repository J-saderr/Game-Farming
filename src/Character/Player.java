package Character;

import ItemSystem.Entities.Tools.Axe;
import ItemSystem.Entities.Tools.Hoe;
import ItemSystem.Entities.Tools.WateringCan;
import Main.*;
import Object.Crop.Carrot;
import Object.Soil.notWateredSoil;
import Object.Soil.wateredSoil;
import ItemSystem.Entities.Seed.*;
import ItemSystem.Entities.Seed.Spinach;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import java.util.StringTokenizer;

public class Player extends Entity {
    KeyHandler keyH;
    private BufferedImage upload;
    private String playerName;
    private int playerDaysPassed;
    public final int screenX;
    public final int screenY;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public ArrayList<Crop> inventoryForCrop = new ArrayList<>(64);
    public final int maxInventorySize = 20;

    private int playerPosition;
    public Entity currentTool;
    public Entity wateringCan;
    public boolean isWater = false;
    public boolean soilWater = false;
    wateredSoil wateredsoil = new wateredSoil();
    notWateredSoil notWateredSoil = new notWateredSoil();

    public int[][] map = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 9, 1},
            {1, 4, 0, 0, 0, 0, 0, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 0, 8, 1},
            {1, 4, 0, 0, 0, 0, 0, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 0, 8, 1},
            {1, 4, 0, 0, 0, 0, 0, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 0, 8, 1},
            {1, 4, 0, 0, 10, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 14, 0, 0, 8, 1},
            {1, 4, 0, 0, 11, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 13, 9, 24, 0, 0, 8, 1},
            {1, 4, 0, 17, 17, 0, 0, 0, 0, 0, 0, 0, 20, 0, 0, 0, 0, 0, 21, 0, 0, 0, 0, 0, 25, 24, 18, 0, 8, 1},
            {1, 4, 0, 17, 17, 0, 0, 0, 0, 0, 0, 0, 0, 0, 19, 0, 0, 0, 0, 19, 0, 0, 0, 0, 25, 24, 18, 0, 8, 1},
            {1, 4, 0, 17, 17, 0, 0, 0, 0, 0, 22, 22, 22, 22, 22, 22, 0, 0, 0, 0, 20, 0, 0, 0, 25, 24, 18, 0, 8, 1},
            {1, 4, 0, 17, 17, 0, 0, 0, 0, 0, 25, 25, 25, 25, 25, 25, 11, 13, 13, 13, 13, 13, 13, 13, 13, 15, 18, 0, 8, 1},
            {1, 4, 0, 17, 17, 0, 0, 0, 0, 0, 25, 25, 25, 25, 25, 25, 10, 12, 12, 12, 12, 12, 12, 12, 7, 24, 18, 0, 8, 1},
            {1, 4, 0, 17, 17, 0, 0, 0, 0, 0, 25, 25, 25, 25, 25, 25, 11, 13, 13, 13, 13, 13, 13, 13, 13, 15, 18, 0, 8, 1},
            {1, 4, 0, 17, 17, 0, 23, 0, 0, 0, 25, 25, 25, 25, 25, 25, 0, 0, 0, 0, 0, 21, 0, 0, 0, 0, 18, 0, 8, 1},
            {1, 4, 0, 17, 17, 0, 0, 0, 23, 0, 22, 22, 22, 22, 22, 22, 0, 0, 0, 0, 19, 0, 0, 0, 0, 0, 18, 0, 8, 1},
            {1, 4, 0, 17, 17, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 23, 0, 0, 18, 0, 8, 1},
            {1, 4, 0, 17, 17, 0, 0, 0, 0, 0, 0, 20, 0, 0, 19, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 18, 0, 8, 1},
            {1, 4, 0, 0, 0, 0, 0, 21, 0, 0, 0, 0, 19, 0, 0, 0, 0, 0, 0, 0, 23, 0, 0, 0, 0, 0, 18, 0, 8, 1},
            {1, 5, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 7, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    };


    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDefault();
        getPlayerImage();
        setItems();
        doingWorkImage();
        //selectItem();
        playerName = name;
    }

    public String getFarmerName() {
        return playerName;
    }

    public void setDefault() {
        worldX = super.gp.tileSize * 5;
        worldY = super.gp.tileSize * 5;
        speed = 4;
        direction = "down";
        wateringCan = new WateringCan(gp);
        currentTool = wateringCan;

    }

    public void setItems() {
        inventory.add(new WateringCan(gp));
        inventory.add(new Hoe(gp));
        inventory.add(new Axe(gp));
        //inventory.add(new Carrot(gp));
        inventory.add(new Potato(gp));
        inventory.add(new Spinach(gp));
    }

    public void getItems(){
        // Button
        // check inventory
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPlayerXPosition() {

        return screenX;
    }
    public int getPlayerYPosition(){
        return screenY;
    }

    ;

    public void update() {
        if (doing) {
            doing();
        }

        if (keyH.up || keyH.down || keyH.right || keyH.left || keyH.enter) {
            if (keyH.up) {
                direction = "up";
            } else if (keyH.down) {
                direction = "down";
            } else if (keyH.left) {
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
            WateringSoil(objIndex);
            //If Collision is False, player can move
            if (!collisionOn) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

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

        public void changeSoil(int i) {
            if (i != 999) {
                String objectName = gp.obj[i].name;

                switch (objectName) {
                    case "Soil":
                        gp.obj[i].image = notWateredSoil.image;
                        break;
                    default:
                        System.out.println("exception");
                        break;
                }
            }
    }

    public void Watering(){
        // check button and tool
        if (keyH.watering
            & currentTool == wateringCan){
        isWater = true;
        }
    }

    public void WateringSoil(int i) {
        //check if isWater = true and player stand on the soil that need to water
        //THIS JUST EXAMPLE FOR THE COORDINATE OF THE SOIL
        // getPlayerXPosition() == gp.obj[].worldX
        if (isWater) {
            if (i != 999) {
                String objectName = gp.obj[i].name;

                switch (objectName) {
                    case "Soil":
                        gp.obj[i].image = wateredsoil.image;
                        gp.obj[i].name = "wateredSoil";
                        break;
                    default:
                        System.out.println("exception");
                        break;
                }

            }
        }
    }

    public void plantCrop(Crop crop){
        // if (crop == )

    }
    public void draw(Graphics2D g2) {
        //g2.setColor(Color.black);
        //g2.dispose();

        BufferedImage image = null;
        if (direction != null) {
            switch (direction) {
                case "left":
                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                    if (spriteNum == 3) {
                        image = left3;
                    }
                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                    if (spriteNum == 3) {
                        image = right3;
                    }
                    break;
                case "up":
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                    if (spriteNum == 3) {
                        image = up3;
                    }
                    break;
                case "down":
                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                    if (spriteNum == 3) {
                        image = down3;
                    }
                    break;
            }
        }
        if (image != null) {
            g2.drawImage(image, screenX, screenY, super.gp.tileSize, super.gp.tileSize, null);
        }
    }
}
