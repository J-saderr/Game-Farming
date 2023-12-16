package Character;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX, worldY;
    public int speed;

    public BufferedImage back1, back2, back3, up1, up2, up3, right1, right2, right3, left1, left2, left3;
    public BufferedImage useAxe1, useAxe2;
    public String direction = "back";

    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;

    public boolean collisonOn = false;
    public int spriterCounter = 0;



}
