package ItemSystem.Entities.Tools;
import Main.*;
import Character.*;

import java.awt.*;

public class WateringCan extends Entity {
    public WateringCan(GamePanel gp){
        super(gp);
        name = "Watercan";
        down1 = setup("res/Tool/WateringCan");
        description = "Watercan";
    }
}