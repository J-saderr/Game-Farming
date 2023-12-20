package ItemSystem.Entities.Seed;

import Main.*;

public class Spinach extends Entity{
    public Spinach(GamePanel gp){
        super(gp);
        name = "Spinach";
        down1 = setup("res/Seed/Spinach");
        description = "Spinach Seed";
    }
}
