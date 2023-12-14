package Character;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class PlayerMove extends Enty{
        GamePanel gp;
        KeyHandler keyH;
        private BufferedImage upload;

        public final int screenX;
        public final int screenY;

        public PlayerMove(GamePanel gp, KeyHandler keyH) {
            this.gp = gp;
            this.keyH = keyH;
            screenX = gp.screenWidth/2 - (gp.tileSize/2);
            screenY = gp.screenHeight/2 - (gp.tileSize/2);
            setDefault();
            getPlayerImage();
        }
        public void setDefault() {
            worldX = gp.tileSize * 10;
            worldY = gp.tileSize * 10;
            speed = 4;
            direction = "left";
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
                back1 = ImageIO.read(inputStream09);
                back2 = ImageIO.read(inputStream10);
                back3 = ImageIO.read(inputStream11);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        public void update() {

            if (keyH.up == true || keyH.down == true || keyH.right == true || keyH.left == true) {
                if (keyH.up) {
                direction = "up";
                worldY -= speed;
            } else if (keyH.down) {
                direction = "down";
                worldY += speed;
            } else if(keyH.left) {
                direction = "left";
                worldX -= speed;
            } else if (keyH.right) {
                direction = "right";
                worldX += speed;
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
                        break;
                    case "up":
                        if(spriteNum == 1) {
                            image = up1;
                        }
                        if(spriteNum == 2) {
                            image = up2;
                        }
                        if(spriteNum == 3) {
                            image = up3;
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
                        break;
                }
            }
            if (image != null) {
                g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
        }
    }
