package ItemSystem.Entities.Tools;

import Main.*;

public class Axe extends Entity {
    public Axe(GamePanel gp){
        super(gp);
        type = type_axe;
        name = "Axe";
        down1 = setup("res/Tool/Axe");
        description = "Axe";
    }
}
