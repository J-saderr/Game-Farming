package Map;
import Main.Entity;
import Main.GamePanel;

public class Collision {
    public GamePanel gp;
    public Collision(GamePanel gp) {
        this.gp = gp;
    }
    public void checkTile( Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction) {
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileManager.Map[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileManager.Map[entityRightCol][entityBottomRow];
                if(gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;

            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileManager.Map[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.Map[entityRightCol][entityTopRow];
                if(gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;

            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileManager.Map[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.Map[entityLeftCol][entityBottomRow];
                if(gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;

            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileManager.Map[entityRightCol][entityTopRow];
                tileNum2 = gp.tileManager.Map[entityRightCol][entityBottomRow];
                if(gp.tileManager.tile[tileNum1].collision == true || gp.tileManager.tile[tileNum2].collision == true) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
    public int checkObject(Entity entity, boolean player){
        //check player hits any object -> yes, return index of object
        int index = 999;
        for(int i = 0; i < gp.obj.length; i++) {
            if(gp.obj[i] != null) {
                //get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidAreaDefaultX;
                entity.solidArea.y = entity.worldY + entity.solidAreaDefaultY;
                //get object's solid area position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidDefaultY;

                switch (entity.direction) {

                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {index = i;}
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {index = i;}
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {index = i;}
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {index = i;}
                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
            }

        }
        return index;
    }
    public int checkEntity(Entity entity, Entity[] target){
        int index = 999;
        for(int i = 0; i < target.length; i++) {
            if(target[i] != null) {
                //get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidAreaDefaultX;
                entity.solidArea.y = entity.worldY + entity.solidAreaDefaultY;
                //get object's solid area position
                target[i].solidArea.x = target[i].worldX + target[i].solidDefaultX;
                target[i].solidArea.y = target[i].worldY + target[i].solidDefaultY;

                switch (entity.direction) {

                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)){
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(target[i].solidArea)){
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                }
                //reloop for loop
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidDefaultX;
                target[i].solidArea.y = target[i].solidDefaultY;

            }
        }
        return index;
    }

    public int checkHouse(Entity entity, Entity[] target){
        int index = 999;
        for(int i = 0; i < target.length; i++) {
            if(target[i] != null) {
                //get entity's solid area position
                entity.solidAreaHouse.x = entity.worldX + entity.solidAreaDefaultX;
                entity.solidAreaHouse.y = entity.worldY + entity.solidAreaDefaultY;
                //get object's solid area position
                target[i].solidAreaHouse.x = target[i].worldX + target[i].solidDefaultX;
                target[i].solidAreaHouse.y = target[i].worldY + target[i].solidDefaultY;

                switch (entity.direction) {

                    case "up":
                        entity.solidAreaHouse.y -= entity.speed;
                        if (entity.solidAreaHouse.intersects(target[i].solidAreaHouse)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "down":
                        entity.solidAreaHouse.y += entity.speed;
                        if (entity.solidAreaHouse.intersects(target[i].solidAreaHouse)) {
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "left":
                        entity.solidAreaHouse.x -= entity.speed;
                        if (entity.solidAreaHouse.intersects(target[i].solidAreaHouse)){
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "right":
                        entity.solidAreaHouse.x += entity.speed;
                        if (entity.solidAreaHouse.intersects(target[i].solidAreaHouse)){
                            entity.collisionOn = true;
                            index = i;
                        }
                        break;
                }
                //reloop for loop
                entity.solidAreaHouse.x = entity.solidAreaDefaultX;
                entity.solidAreaHouse.y = entity.solidAreaDefaultY;
                target[i].solidAreaHouse.x = target[i].solidDefaultX;
                target[i].solidAreaHouse.y = target[i].solidDefaultY;

            }
        }
        return index;
    }
}