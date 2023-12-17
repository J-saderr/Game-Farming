package Objects.Plants;

import Objects.SuperObjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
public class Spinach extends SuperObjects { // Corrected class name
    public Spinach() {
        name = "Spinach";
        collision = false;
        try {
            InputStream inputStream99 = new FileInputStream(new File("res/Plants/2.png"));
            image = ImageIO.read(inputStream99); // Assign the read image to the image variable
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
