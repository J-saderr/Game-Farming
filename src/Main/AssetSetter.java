package Main;
import Character.*;
import HouseLevel.House;
import Money.Money;
import Object.Soil.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }
    public void setObject() {
        gp.obj[0] = new Soil(gp);
        gp.obj[0].worldX = 10 * gp.tileSize;
        gp.obj[0].worldY = 10 * gp.tileSize;

        gp.obj[1] = new Soil(gp);
        gp.obj[1].worldX = 11 * gp.tileSize;
        gp.obj[1].worldY = 10 * gp.tileSize;

        gp.obj[2] = new Soil(gp);
        gp.obj[2].worldX = 12 * gp.tileSize;
        gp.obj[2].worldY = 10 * gp.tileSize;

        gp.obj[3] = new Soil(gp);
        gp.obj[3].worldX = 13 * gp.tileSize;
        gp.obj[3].worldY = 10 * gp.tileSize;

        gp.obj[4] = new Soil(gp);
        gp.obj[4].worldX = 14 * gp.tileSize;
        gp.obj[4].worldY = 10 * gp.tileSize;

        gp.obj[5] = new Soil(gp);
        gp.obj[5].worldX = 15 * gp.tileSize;
        gp.obj[5].worldY = 10 * gp.tileSize;

        gp.obj[6] = new Soil(gp);
        gp.obj[6].worldX = 10 * gp.tileSize;
        gp.obj[6].worldY = 11 * gp.tileSize;

        gp.obj[7] = new Soil(gp);
        gp.obj[7].worldX = 10 * gp.tileSize;
        gp.obj[7].worldY = 12 * gp.tileSize;

        gp.obj[8] = new Soil(gp);
        gp.obj[8].worldX = 10 * gp.tileSize;
        gp.obj[8].worldY = 13 * gp.tileSize;

        gp.obj[9] = new Soil(gp);
        gp.obj[9].worldX = 11 * gp.tileSize;
        gp.obj[9].worldY = 11 * gp.tileSize;

        gp.obj[10] = new Soil(gp);
        gp.obj[10].worldX = 11 * gp.tileSize;
        gp.obj[10].worldY = 12 * gp.tileSize;

        gp.obj[11] = new Soil(gp);
        gp.obj[11].worldX = 11 * gp.tileSize;
        gp.obj[11].worldY = 13 * gp.tileSize;

        gp.obj[12] = new Soil(gp);
        gp.obj[12].worldX = 12 * gp.tileSize;
        gp.obj[12].worldY = 11 * gp.tileSize;

        gp.obj[13] = new Soil(gp);
        gp.obj[13].worldX = 12 * gp.tileSize;
        gp.obj[13].worldY = 12 * gp.tileSize;

        gp.obj[14] = new Soil(gp);
        gp.obj[14].worldX = 12 * gp.tileSize;
        gp.obj[14].worldY = 13 * gp.tileSize;

        gp.obj[15] = new Soil(gp);
        gp.obj[15].worldX = 13 * gp.tileSize;
        gp.obj[15].worldY = 11 * gp.tileSize;

        gp.obj[16] = new Soil(gp);
        gp.obj[16].worldX = 13 * gp.tileSize;
        gp.obj[16].worldY = 12 * gp.tileSize;

        gp.obj[17] = new Soil(gp);
        gp.obj[17].worldX = 13 * gp.tileSize;
        gp.obj[17].worldY = 13 * gp.tileSize;

        gp.obj[18] = new Soil(gp);
        gp.obj[18].worldX = 14 * gp.tileSize;
        gp.obj[18].worldY = 11 * gp.tileSize;

        gp.obj[19] = new Soil(gp);
        gp.obj[19].worldX = 14 * gp.tileSize;
        gp.obj[19].worldY = 12 * gp.tileSize;

        gp.obj[20] = new Soil(gp);
        gp.obj[20].worldX = 14 * gp.tileSize;
        gp.obj[20].worldY = 13 * gp.tileSize;

        gp.obj[21] = new Soil(gp);
        gp.obj[21].worldX = 15 * gp.tileSize;
        gp.obj[21].worldY = 11 * gp.tileSize;

        gp.obj[22] = new Soil(gp);
        gp.obj[22].worldX = 15 * gp.tileSize;
        gp.obj[22].worldY = 12 * gp.tileSize;

        gp.obj[23] = new Soil(gp);
        gp.obj[23].worldX = 15 * gp.tileSize;
        gp.obj[23].worldY = 13 * gp.tileSize;

    }
    public void setNPC() {
        gp.npc[0] = new MerchantNPC(gp);
        gp.npc[0].worldX = gp.tileSize * 15;
        gp.npc[0].worldY = gp.tileSize * 15;
    }

    public void setHouse() {
        gp.house[0] = new House(gp);
        gp.house[0].worldX = gp.tileSize+10;
        gp.house[0].worldY = -20;
    }

}
