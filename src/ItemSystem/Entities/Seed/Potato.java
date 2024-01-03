package ItemSystem.Entities.Seed;

import Main.*;

import java.awt.*;

public class Potato extends Entity{
    public Potato (GamePanel gp){
        super(gp);
        name = "Potato";
        price = 75;
        down1 = setup("res/Seed/Potatoseed");
        description = "Potato Seed";
    }
}