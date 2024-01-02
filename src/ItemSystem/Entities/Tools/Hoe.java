package ItemSystem.Entities.Tools;

import Main.*;

import java.awt.*;

public class Hoe extends Entity{
    public Hoe(GamePanel gp){
        super(gp);
        type = type_hoe;
        name = "Hoe";
        down1 = setup("res/Tool/Hoe");
        description = "Hoe";
    }
}
