package ItemSystem.Entities.Seed;

import Main.*;

import java.awt.*;

public class Spinach extends Entity{
    public Spinach(GamePanel gp){
        super(gp);
        name = "Spinach";
        down1 = setup("res/Seed/Spinachseed");
        description = "Spinach Seed";
    }
}
