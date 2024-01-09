package ItemSystem.Entities.Soil;
import Main.Entity;
import Main.GamePanel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
public class Soil extends Entity {
    public Soil(GamePanel gp) {
        super(gp);
        name = "Soil";
        collision = false;
        try {
            InputStream inputStream12 = new FileInputStream(new File("res/Soil/Soil.png"));
            image = ImageIO.read(inputStream12);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
