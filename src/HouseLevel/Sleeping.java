package HouseLevel;
import Main.*;

public class Sleeping {
    GamePanel gp;

    public Sleeping(GamePanel gp) {
        this.gp = gp;
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
