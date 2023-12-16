package Entity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import main.GamePanel;

public class PlayerMove extends entity {
        GamePanel gp;
        KeyHandler keyH;
        private BufferedImage upload;
        public final int screenX;
        public final int screenY;

        public PlayerMove(GamePanel gp, KeyHandler keyH) {
            super(gp);
            this.keyH = keyH;
            screenX = gp.screenWidth/2 - (gp.tileSize/2);
            screenY = gp.screenHeight/2 - (gp.tileSize/2);

            solidArea = new Rectangle(2, 12, 14, 14); //player 16x16
            solidAreaDefaultX = solidArea.x;
            solidAreaDefaultY = solidArea.y;

            setDefault();
            getPlayerImage();
        }
        public void setDefault() {
            worldX = 100;
            worldY = 100;
            speed = 4;
            direction = "left";
        }
        public void getPlayerImage() {
            try
                (InputStream inputStream00 = new FileInputStream(new File("res/player/left1.png"));
                 InputStream inputStream01 = new FileInputStream(new File("res/player/left2.png"));
                 InputStream inputStream02 = new FileInputStream(new File("res/player/left3.png"));
                 InputStream inputStream03 = new FileInputStream(new File("res/player/left4.png"));
                 InputStream inputStream04 = new FileInputStream(new File("res/player/right1.png"));
                 InputStream inputStream05 = new FileInputStream(new File("res/player/right2.png"));
                 InputStream inputStream06 = new FileInputStream(new File("res/player/right3.png"));
                 InputStream inputStream07 = new FileInputStream(new File("res/player/right4.png"));
                 InputStream inputStream08 = new FileInputStream(new File("res/player/back1.png"));
                 InputStream inputStream09 = new FileInputStream(new File("res/player/back2.png"));
                 InputStream inputStream10 = new FileInputStream(new File("res/player/back3.png"));
                 InputStream inputStream11 = new FileInputStream(new File("res/player/back4.png"));
                 InputStream inputStream12 = new FileInputStream(new File("res/player/front1.png"));
                 InputStream inputStream13 = new FileInputStream(new File("res/player/front2.png"));
                 InputStream inputStream14 = new FileInputStream(new File("res/player/front3.png"));
                 InputStream inputStream15 = new FileInputStream(new File("res/player/front4.png"))) {
                left1 = ImageIO.read(inputStream00);
                left2 = ImageIO.read(inputStream01);
                left3 = ImageIO.read(inputStream02);
                left4 = ImageIO.read(inputStream03);
                right1 = ImageIO.read(inputStream04);
                right2 = ImageIO.read(inputStream05);
                right3 = ImageIO.read(inputStream06);
                right4 = ImageIO.read(inputStream07);
                front1 = ImageIO.read(inputStream08);
                front2 = ImageIO.read(inputStream09);
                front3 = ImageIO.read(inputStream10);
                front4 = ImageIO.read(inputStream11);
                back1 = ImageIO.read(inputStream12);
                back2 = ImageIO.read(inputStream13);
                back3 = ImageIO.read(inputStream14);
                back3 = ImageIO.read(inputStream15);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        public void update() {

            if (keyH.up || keyH.down || keyH.right || keyH.left) {
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
            gp.collide.checkTile(this);

            //Check Object Collision
            int objIndex = gp.collide.checkObject(this, true);
            //If Collision is False, player can move
            if (!collisionOn) {
                switch(direction) {
                    case "up": worldY -= speed; break;
                    case "down": worldY += speed; break;
                    case "left": worldX -= speed; break;
                    case "right": worldX += speed; break;
                }
            }


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
        public void draw (Graphics2D g2) {
            //g2.setColor(Color.black);
            //g2.dispose();

            BufferedImage image = null;
            if (direction != null) {
                switch (direction) {
                    case "left":
                        if(spriteNum == 1) {
                            image = left1;
                        }
                        if(spriteNum == 2) {
                            image = left2;
                        }
                        if(spriteNum == 3) {
                            image = left3;
                        }
                        if(spriteNum == 4) {
                            image = left4;
                        }
                        break;
                    case "right":
                        if(spriteNum == 1) {
                            image = right1;
                        }
                        if(spriteNum == 2) {
                            image = right2;
                        }
                        if(spriteNum == 3) {
                            image = right3;
                        }
                        if(spriteNum == 4) {
                            image = right4;
                        }
                        break;
                    case "up":
                        if(spriteNum == 1) {
                            image = front1;
                        }
                        if(spriteNum == 2) {
                            image = front2;
                        }
                        if(spriteNum == 3) {
                            image = front3;
                        }
                        if(spriteNum == 4) {
                            image = front4;
                        }
                        break;
                    case "down":
                        if(spriteNum == 1) {
                            image = back1;
                        }
                        if(spriteNum == 2) {
                            image = back2;
                        }
                        if(spriteNum == 3) {
                            image = back3;
                        }
                        if(spriteNum == 4) {
                            image = back4;
                        }
                        break;
                }
            }
            if (image != null) {
                g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
            }
        }
    }
