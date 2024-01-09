package ItemSystem.Entities.Crop;

import Main.GamePanel;

public class CarrotMature extends Carrot {
    private GamePanel gp;

    public CarrotMature(GamePanel gp) {
        super(gp);
        price = 70;
        type = type_carrot_mature;
        down1 = setup("res/Plants/4");
        description = "Carrot";
    }
}
