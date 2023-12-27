package ItemSystem.Entities.Tools;

import Main.*;

import java.awt.*;

public class Hoe extends Entity{
    public Hoe(GamePanel gp){
        super(gp);
        name = "Hoe";
        down1 = setup("res/Tool/Hoe");
        description = "Hoe";
    }
}
