package Objects.Plants;
import Objects.SuperObjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
public class Potato extends SuperObjects { // Corrected class name
    public Potato() {
        name = "Potato";
        collision = false;
        try {
            InputStream inputStream98 = new FileInputStream(new File("res/Plants/3.png"));
            image = ImageIO.read(inputStream98); // Assign the read image to the image variable
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
