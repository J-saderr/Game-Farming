package ItemSystem.Entities.Crop;

import Main.GamePanel;

public class SpinachMature extends Spinach{
    public SpinachMature(GamePanel gp){
        super(gp);
        price = 120;
        name = "Spinach Mature";
        type = type_spinach_mature;
        down1 = setup("res/Plants/5");
        stackable = true;
        description = "Spinach";
    }
}

