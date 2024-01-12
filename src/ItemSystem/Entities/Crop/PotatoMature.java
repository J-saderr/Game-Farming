package ItemSystem.Entities.Crop;

import Main.GamePanel;

public class PotatoMature extends Potato{
    public PotatoMature(GamePanel gp){
        super(gp);
        price = 90;
        name = "Potato Mature";
        type = type_potato_mature;
        down1 = setup("res/Plants/6");
        stackable = true;
        description = "Potato ";
    }
}

