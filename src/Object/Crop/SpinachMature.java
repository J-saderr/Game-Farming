package Object.Crop;

import Main.Entity;
import Main.GamePanel;

public class SpinachMature extends Spinach{
    private GamePanel gp;
    public SpinachMature(GamePanel gp){
        super(gp);
        type = type_spinach_mature;
        down1 = setup("res/Plants/6");
        description = "Spinach x "+ super.quantities;
    }
}

