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
            InputStream inputStreamxx = new FileInputStream(new File("res/Plants/6.png"));
            image = ImageIO.read(inputStreamxx); // Assign the read image to the image variable
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
