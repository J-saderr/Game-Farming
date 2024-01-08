package Object.Crop;

import Main.Entity;
import Main.GamePanel;

public class PotatoMature extends Potato{
    private GamePanel gp;
    public PotatoMature(GamePanel gp){
        super(gp);
        type = type_potato_mature;
        down1 = setup("res/Plants/5");
        description = "Potato x "+ super.quantities;
    }
}

