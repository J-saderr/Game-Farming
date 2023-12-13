package Character;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlayerMove extends Enty{
    GamePanel gp;
    KeyHandler keyH;
    private BufferedImage upload;

    public PlayerMove(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
    }
    public void setDefault() {
        x = 100;
        y = 100;
        speed = 4;
    }
    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void update() {
        if (keyH.up) {
            y -= speed;
        } else if (keyH.down) {
            y += speed;
        } else if(keyH.left) {
            x -= speed;
        } else if (keyH.right) {
            x += speed;
        }
    }
    public void draw (Graphics2D g2) {
        g2.setColor(Color.white);
        g2.dispose();
    }
}
