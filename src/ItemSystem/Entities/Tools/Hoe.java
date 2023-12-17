package ItemSystem.Entities.Tools;

import Main.*;

public class Hoe extends Entity{
    public Hoe(GamePanel gp){
        super(gp);
        name = "Hoe";
        down1 = setup("res/Tool/Hoe");
        description = "Use to tree";
    }
}
