package Entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class entity {
    public final GamePanel gp;
    public int worldX, worldY;
    public int speed;

    public BufferedImage back1, back2, back3, back4, front1, front2, front3, front4, right1, right2, right3, right4, left1, left2, left3, left4;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;
    //Solid rectangle for player for collision
    public Rectangle solidArea;
    //Solid coordinates for checking collision between players and objects (including NPC)
    public int solidAreaDefaultX;
    public int solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLock = 0;
    public entity(GamePanel gp) {
        this.gp = gp;
    }
    public void draw(Graphics2D g2) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        BufferedImage image = null ;
        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }
        if (direction != null) {
            switch (direction) {
                case "face":
                    if(spriteNum == 1) {
                        image = npc;
                    }
                    break;

            }
        }
        if (image != null) {
            g2.drawImage(image, worldX, worldY, gp.tileSize, gp.tileSize, null);
        }

    }
}
