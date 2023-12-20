package ItemSystem.Entities.Tools;

import Main.*;

public class Axe extends Entity {
    public Axe(GamePanel gp){
        super(gp);
        name = "Axe";
        down1 = setup("res/Tool/Axe");
        description = "Axe";
    }
}
