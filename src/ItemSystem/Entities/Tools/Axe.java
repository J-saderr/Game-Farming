package ItemSystem.Entities.Tools;

import Main.*;

import java.awt.*;

public class Axe extends Entity {
    public Axe(GamePanel gp){
        super(gp);
        name = "Axe";
        down1 = setup("res/Tool/Axe");
        description = "Axe";
    }
}
