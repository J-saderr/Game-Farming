package ItemSystem.Entities.Tools;
import Main.*;
import Character.*;

public class WateringCan extends Entity {
    public WateringCan(GamePanel gp){
        super(gp);
        name = "Watercan";
        down1 = setup("res/Tool/WateringCan");
        description = "Use to water tree";
    }
}