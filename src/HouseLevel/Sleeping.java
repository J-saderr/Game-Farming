package HouseLevel;
import Main.*;
import Character.*;

import java.awt.*;

public class Sleeping {
    GamePanel gp;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    public Sleeping(GamePanel gp) {
        this.gp = gp;
//        eventRect = new Rectangle();
//        eventRect.x = 23;
//        eventRect.y = 23;
//        eventRect.width = 250;
//        eventRect.height = 250;
//        eventRectDefaultX = eventRect.x;
//        eventRectDefaultY = eventRect.y;
    }
    public void checkSleep() {
        if (hit()) {housePower();}
    }
    public boolean hit() {
        boolean hit = false;
        int houseIndex = gp.collision.checkHouse(gp.player, gp.house);
        if (houseIndex != 999) {
            hit = true;
        }
        return hit;
    }
    public void housePower() {
        gp.gameState = gp.houseState;
    }
}
