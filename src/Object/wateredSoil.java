package Object;
import Main.Entity;
import Main.GamePanel;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
public class wateredSoil extends Entity{ // Corrected class name
    public wateredSoil(GamePanel gp) {
        super(gp);
        name = "wateredSoil";
        collision = true;
        try {
            InputStream inputStream14 = new FileInputStream(new File("res/Soil/Water.png"));
            image = ImageIO.read(inputStream14);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}