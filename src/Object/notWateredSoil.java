package Object;

import Main.Entity;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
public class notWateredSoil extends Entity { // Corrected class name
    public notWateredSoil(GamePanel gp) {
        super(gp);
        name = "Soil";
        collision = true;
        try {
            InputStream inputStream14 = new FileInputStream(new File("res/Soil/Notwater.png"));
            image = ImageIO.read(inputStream14);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
