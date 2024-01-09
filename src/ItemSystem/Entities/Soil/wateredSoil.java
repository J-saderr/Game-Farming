package ItemSystem.Entities.Soil;
import Main.Entity;
import Main.GamePanel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
public class wateredSoil extends Entity { // Corrected class name
    public wateredSoil(GamePanel gp) {
        super(gp);
        name = "wateredSoil";
        collision = false;
        try {
            InputStream inputStream14 = new FileInputStream(new File("res/Soil/wateredsoil.png"));
            image = ImageIO.read(inputStream14);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
