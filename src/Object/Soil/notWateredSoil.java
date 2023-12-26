package Object.Soil;
import Object.SuperObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
public class notWateredSoil extends SuperObject { // Corrected class name
    public notWateredSoil() {
        name = "Soil";
        collision = true;
        try {
            InputStream inputStream13 = new FileInputStream(new File("res/Soil/notwateredsoil.png"));
            image = ImageIO.read(inputStream13);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
