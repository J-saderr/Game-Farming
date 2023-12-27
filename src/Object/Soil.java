package Object;

import Main.Entity;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Soil extends Entity {
    public Soil(GamePanel gp) {
        super(gp);
        name = "Soil";
        collision = true;
        try {
            InputStream inputStream14 = new FileInputStream(new File("res/Soil/Water.png"));
            image = ImageIO.read(inputStream14);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
