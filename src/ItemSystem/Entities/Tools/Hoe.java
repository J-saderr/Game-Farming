package ItemSystem.Entities.Tools;

import Main.*;

public class Hoe extends Entity{
    public Hoe(GamePanel gp){
        super(gp);
        type = type_hoe;
        name = "Hoe";
        down1 = setup("res/Tool/Hoe");
        description = "Hoe";
    }
}
