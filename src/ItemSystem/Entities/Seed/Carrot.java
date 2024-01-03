package ItemSystem.Entities.Seed;

import Main.*;

import java.awt.*;

public class Carrot extends Entity{
    public Carrot(GamePanel gp){
        super(gp);
        name = "Carrot";
        price = 50;
        down1 = setup("res/Seed/Carrotseed");
        description = "Carrot Seed";
    }
}