package Main;

import java.awt.*;

public class EventHandler {
    public GamePanel gp;
    EventRect eventRect[][];
    //Prevent events happen repeatedly
    int previousEventX; int previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gp){
        this.gp = gp;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
        int col = 0; int row = 0;
        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRecDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRecDefaultY = eventRect[col][row].y;

            col++;
            if (col == gp.maxWorldCol) {col = 0; row++;}
        }


    }
    public void checkEvent(){
        //TRIGGER EVENT TO HAPPEN
        //CHECK IF THE PLAYER IS MORE THAN 1 TILE FROM THE EVENT
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance,yDistance);
        if(distance >gp.tileSize){ canTouchEvent = true;}
        if(canTouchEvent ) {
            if (hit(15, 15, "any")) {speak(gp.npc[0]);}
        }
    }

    public boolean hit(int col, int row, String reqDirection){
        boolean hit = false;
        gp.player.solidArea.x = gp.player.worldX +gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldX +gp.player.solidArea.y;
        eventRect[col][row].x = col * gp.tileSize + eventRect[col][row].x;
        eventRect[col][row].y = row * gp.tileSize + eventRect[col][row].y;

        if (gp.player.solidArea.intersects(eventRect[col][row]) && !eventRect[col][row].eventDone) {
            if (gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;

                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }

        gp.player.solidArea.x = gp.player.solidDefaultX;
        gp.player.solidArea.y = gp.player.solidDefaultY;
        eventRect[col][row].x = eventRect[col][row].eventRecDefaultX;
        eventRect[col][row].y = eventRect[col][row].eventRecDefaultY;

        return hit;
    }
    public void speak (Entity entity) {
        if (gp.keyH.enter ){
            gp.gameState = gp.dialogueState;
            entity.speak();
        }
    }
}
