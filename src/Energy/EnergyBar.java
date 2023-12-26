package Energy;

import Main.GamePanel;
import Objects.SuperObjects;
import ItemSystem.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class EnergyBar extends SuperObjects {
    GamePanel gp;
    public EnergyBar(GamePanel gp) {
        this.gp = gp;
        name = "Bar";
        try {
            InputStream input = new FileInputStream(new File("res/Energy/energy.png"));
            InputStream input0 = new FileInputStream(new File("res/Energy/energybar0.png"));
            enbar = ImageIO.read(input);
            enbar0 = ImageIO.read(input0);

            //uTool.scaleImage(image, 50, 50);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
