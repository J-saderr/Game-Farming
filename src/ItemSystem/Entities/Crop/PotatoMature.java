package ItemSystem.Entities.Crop;

import Main.GamePanel;

public class PotatoMature extends Potato{
    private GamePanel gp;
    public PotatoMature(GamePanel gp){
        super(gp);
        price = 90;
        type = type_potato_mature;
        down1 = setup("res/Plants/5");
        description = "Potato x "+ super.quantities;
    }
}

