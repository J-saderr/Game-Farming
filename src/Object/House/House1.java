package Object.Soil;
import Object.SuperObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
public class House1 extends SuperObject { // Corrected class name
    public House1() {
        name = "Houselv1"; // This assigns a string to name, this is correct.
        collision = false; // You may want to set this as true or false depending on your game logic
        try {
            // You want to read the image file and assign it to this object's image field.
            InputStream inputStream13 = new FileInputStream(new File("res/Soil/Soil.png"));
            image = ImageIO.read(inputStream13); // Assign the read image to the image variable
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
