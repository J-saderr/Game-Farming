package ItemSystem.Entities.Seed;

import Main.*;

public class Carrot extends Entity{
    public Carrot(GamePanel gp){
        super(gp);
        name = "Carrot";
        down1 = setup("res/Seed/Carrot");
        description = "Carrot Seed";
    }
}