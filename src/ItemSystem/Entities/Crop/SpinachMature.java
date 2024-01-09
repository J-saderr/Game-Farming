package ItemSystem.Entities.Crop;

import Main.GamePanel;

public class SpinachMature extends Spinach{
    private GamePanel gp;
    public SpinachMature(GamePanel gp){
        super(gp);
        price = 120;
        type = type_spinach_mature;
        down1 = setup("res/Plants/5");
        description = "Spinach x "+ super.quantities;
    }
}

