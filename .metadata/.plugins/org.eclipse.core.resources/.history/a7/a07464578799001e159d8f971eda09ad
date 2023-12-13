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

        public PlayerMove(GamePanel gp, KeyHandler keyH) {
            this.gp = gp;
            this.keyH = keyH;
            setDefault();
        }
        public void setDefault() {
            x = 100;
            y = 100;
            speed = 4;
            direction = "left";
        }
        public void getPlayerImage() {
            try (InputStream inputStream00 = new FileInputStream(new File("res/player/left1.png"));
                 InputStream inputStream01 = new FileInputStream(new File("res/player/right1.png"))) {
                left1 = ImageIO.read(inputStream00);
                right1 = ImageIO.read(inputStream01);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        public void update() {
            if (keyH.up) {
                direction = "up";
                y -= speed;
            } else if (keyH.down) {
                direction = "down";
                y += speed;
            } else if(keyH.left) {
                direction = "left";
                x -= speed;
            } else if (keyH.right) {
                direction = "right";
                x += speed;
            }
        }
        public void draw (Graphics2D g2) {
            //g2.setColor(Color.black);
            //g2.dispose();

            BufferedImage image = null;
            if (direction != null) {
                switch (direction) {
                    case "left":
                        image = left1;
                        break;
                    case "right":
                        image = right1;
                        break;
                    // Add other cases if needed
                }
            }
            if (image != null) {
                g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
            }
        }
    }
