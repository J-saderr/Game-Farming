package ItemSystem.Entities.Crop;

import Main.GamePanel;

public class CarrotMature extends Carrot {

    public CarrotMature(GamePanel gp) {
        super(gp);
        price = 70;
        name = "Carrot Mature";
        type = type_carrot_mature;
        down1 = setup("res/Plants/4");
        description = "Carrot";
        stackable = true;

    }
}
